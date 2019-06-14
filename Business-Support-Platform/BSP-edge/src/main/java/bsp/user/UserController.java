package bsp.user;

import bsp.redis.RedisClient;
import bsp.response.LoginResponse;
import bsp.response.Response;
import bsp.util.DefaultUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sup.user.User;
import sup.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    //@Autowired
    @Reference(registry = "zookeeper://127.0.0.1:2181",mock = "true",check = false)
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {"base-customer.xml"});
        context.start();
        // Obtaining a remote service proxy
        UserService userService = (UserService)context.getBean("userService");
        // Executing remote methods
        User user = userService.getUserById(1);
        // Display the call result
        System.out.println(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam String username,
                          @RequestParam("password")String password){
        User user = userService.getUserById(1);
        if(user==null) return Response.USERNAME_PASSWORD_INVALID;
        if(!user.getPassword().equalsIgnoreCase(DefaultUtils.md5(password)))
            return Response.USERNAME_PASSWORD_INVALID;
        String token = DefaultUtils.genToken();
        redisClient.set(token,user,3600);
        return new LoginResponse(token);
    }

    @RequestMapping("/register")
    @ResponseBody
    public Response register(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             @RequestParam(value = "realname",required = false)String realname,
                             @RequestParam(value = "mobile",required = false)String mobile,
                             @RequestParam(value = "email",required = false)String email,
                             @RequestParam(value = "verifyCode")String verifyCode){
        if(StringUtils.isBlank(mobile) && StringUtils.isBlank(email)){
            return Response.MOBILE_OR_EMAIL_REQUIED;
        }
        if(StringUtils.isNotBlank(mobile)){
            String redisCode = redisClient.get(mobile);
            if(!StringUtils.equalsIgnoreCase(redisCode,verifyCode)){
                return Response.VERIFYCODE_INVALID;
            }
        }else {
            String redisCode = redisClient.get(email);
            if(!StringUtils.equalsIgnoreCase(redisCode,verifyCode)){
                return Response.VERIFYCODE_INVALID;
            }
        }
        User userInfo = new User();
        userInfo.setUsername(username);
        userInfo.setPassword(DefaultUtils.md5(password));
        userInfo.setEmail(email);
        try{
            userService.regiserUser(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }

    //http://localhost:8082/user/authentication
    @RequestMapping(value = "/authentication",method = RequestMethod.POST)
    @ResponseBody
    public User authentication(@RequestHeader("token")String token){

        return redisClient.get(token);
    }

}
