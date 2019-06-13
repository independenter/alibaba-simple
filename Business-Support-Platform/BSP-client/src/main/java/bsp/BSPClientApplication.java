package bsp;

import bsp.client.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sup.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@Controller
public class BSPClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(BSPClientApplication.class,args);
    }

    //http://localhost:8008/login?token=otijxr1lt48zip0f359bbyapwh5rdvim
    @RequestMapping("/login")
    @ResponseBody
    public User login(HttpServletRequest request){
        return (User)request.getAttribute("user");
    }
//
//    @Bean
//    public FilterRegistrationBean authFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new LoginFilter());
//        registration.addUrlPatterns("/webapi/*"); //
//        registration.addInitParameter("paramName", "paramValue"); //
//        registration.setName("authFilter");
//        registration.setOrder(2);
//        return registration;
//    }
}
