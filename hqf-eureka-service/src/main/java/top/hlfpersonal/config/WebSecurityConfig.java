package top.hlfpersonal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @program: hqf-parent
 * @description: 需要关闭csrf才能连接
 * @author: feng
 * @create: 2019-03-27 15:43
 **/
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(); //关闭csrf
//        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

    }

}