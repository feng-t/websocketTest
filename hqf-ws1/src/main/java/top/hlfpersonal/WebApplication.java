package top.hlfpersonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import top.hlfpersonal.util.IdWorker;

/**
 * @program: hqf-parent
 * @description: web启动类
 * @author: feng
 * @create: 2019-03-26 22:27
 **/
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
@EnableEurekaClient
public class WebApplication {
    @Bean
    public IdWorker setID(){
        return new IdWorker();
    }

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
