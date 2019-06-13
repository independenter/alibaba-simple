package bsp.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;
import sup.user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

//@Component
//@WebFilter(urlPatterns = "/client/login", filterName = "authFilter")
public class LoginFilter implements Filter {

    public static void main(String[] args) {
        LoginFilter.requestUserInfo("otijxr1lt48zip0f359bbyapwh5rdvim");
    }

    private static Cache<String, User> cache = CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String token = request.getParameter("token");
        if(StringUtils.isBlank(token)){
            Cookie[] cookies = request.getCookies();
            if(cookies.length>0){
                for(Cookie c:cookies){
                    if(c.getName().equals("token")){
                        token=c.getValue();
                    }
                }
            }
        }
        User User = null;
        if(StringUtils.isNotBlank(token)){
            User = cache.getIfPresent(token);
            if(User==null) {
                User = requestUserInfo(token);
                cache.put(token,User);
            }
        }
        if(token==null){
            response.sendRedirect("http://localhost:8080/user/login");
        }

        login(request,response,User);

        filterChain.doFilter(request,response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response, User User){
        request.setAttribute("user",User);
    };

    private static User requestUserInfo(String token) {
        String url = "http://127.0.0.1:8080/user/authentication";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("token",token);
        InputStream inputStream = null;
        try{
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
                throw new RuntimeException("request userinfo failed!StatusLine"+response.getStatusLine());
            }
            inputStream = response.getEntity().getContent();
            byte[] temp = new byte[1024];
            StringBuilder sb =new StringBuilder();
            int len = 0;
            while ((len = inputStream.read(temp))>0){
                sb.append(new String(temp,0,len));
            }
            User User = new ObjectMapper().readValue(sb.toString(),User.class);
            return User;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new User();
    }

    public void destroy() {

    }
}
