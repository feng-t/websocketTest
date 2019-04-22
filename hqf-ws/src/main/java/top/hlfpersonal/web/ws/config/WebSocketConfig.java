package top.hlfpersonal.web.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: hqf-parent
 * @description: websocket配置
 * @author: feng
 * @create: 2019-03-28 22:15
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //全局使用的订阅前缀(客户端订阅路径上会体现出来)
        registry.enableSimpleBroker("/queue","/topic");
        //点对点使用的订阅前缀(客户端订阅路径上会体现出来)，不设置的话，默认也是/user/
        registry.setApplicationDestinationPrefixes("/app");
    }
}
