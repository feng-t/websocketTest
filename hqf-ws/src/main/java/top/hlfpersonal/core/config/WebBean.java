package top.hlfpersonal.core.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @program: hqf-parent
 * @description: javabean 管理
 * @author: feng
 * @create: 2019-03-26 22:16
 **/
@Configuration
public class WebBean {
    @Bean
    public ErrorPageRegistrar containerCustomizer() {
        return (container -> {
            ErrorPage[] errorPages=new ErrorPage[2];
            errorPages[0] = new ErrorPage(HttpStatus.UNAUTHORIZED, "/500.html");
            errorPages[1] = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            container.addErrorPages(errorPages);
        });
    }
}
