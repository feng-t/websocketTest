package top.hlfpersonal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import top.hlfpersonal.util.IdWorker;

/**
 * @program: HQF_parent
 * @description: eureke service
 * @author: feng
 * @create: 2019-03-26 15:52
 **/
@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class EurekaService  {
    public static void main(String[] args) {
        SpringApplication.run(EurekaService.class,args);
    }

    /**
     * 分布式id
     * @return
     */
//    @Bean
//    public IdWorker setID(){
//        return new IdWorker();
//    }
}
