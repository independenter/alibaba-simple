package bsp;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import bsp.client.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;

@SpringBootApplication()
@RestController
@ServletComponentScan(basePackageClasses = LoginFilter.class)
//@ImportResource("classpath:base-customer.xml")
public class BSPEdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BSPEdgeApplication.class,args);
    }

//    @Bean
//    public UserService getUserService(){
//        return new UserServiceImpl();
//    }

    @RequestMapping("/hi")
    public String hi(){
        return "Hi";
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(commonDataFilter());
        registration.addUrlPatterns("/hi");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "loginFilter")
    public Filter commonDataFilter() {
        return new LoginFilter();
    }
}
