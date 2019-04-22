package top.hlfpersonal.core.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.*;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-26 21:01
 **/
@Configuration
@Slf4j
public class ShiroConfig {
    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm();
    }
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        listeners.add(new ShiroSessionListener());
//        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }
    @Bean
    public SessionDAO sessionDAO() {
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        return sessionDAO;
    }
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->

        // 配置不被拦截的资源及链接
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/jquery.min.js", "anon");
        filterChainDefinitionMap.put("/app_s/**", "anon");
        filterChainDefinitionMap.put("/tologin", "anon");
        filterChainDefinitionMap.put("/user/**", "anon");

        //配置需要认证权限的
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面，即本文使用的login.html
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/ws");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator dp=new DefaultAdvisorAutoProxyCreator();
        dp.setUsePrefix(true);
//        log.info();

        return dp;
    }

    //配置异常处理，不配置的话没有权限后台报错，前台不会跳转到403页面
    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
        mappings.setProperty("UnauthorizedException","403");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);  // None by default
        simpleMappingExceptionResolver.setDefaultErrorView("error");    // No default
        simpleMappingExceptionResolver.setExceptionAttribute("ex");     // Default is "exception"
        return simpleMappingExceptionResolver;
    }

//    @Bean
//    public SessionDAO sessionDAO() {
//        return new MemorySessionDAO();
//    }

}
