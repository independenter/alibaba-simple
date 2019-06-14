package sup.base;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BSPBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BSPBaseApplication.class,args);
    }

//    // 注册中心
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://localhost:2181");
//        // 注册简化版的的url到注册中心
//        registryConfig.setSimplified(true);
//        return registryConfig;
//    }
//
//
//    // 元数据中心
//    @Bean
//    public MetadataReportConfig metadataReportConfig() {
//        MetadataReportConfig metadataReportConfig = new MetadataReportConfig();
//        metadataReportConfig.setAddress("zookeeper://localhost:2181");
//        return metadataReportConfig;
//    }
//
//
//    // 配置中心
//    @Bean
//    public ConfigCenterConfig configCenterConfig() {
//        ConfigCenterConfig configCenterConfig = new ConfigCenterConfig();
//        configCenterConfig.setAddress("zookeeper://127.0.0.1:2181");
//        return configCenterConfig;
//    }
}
