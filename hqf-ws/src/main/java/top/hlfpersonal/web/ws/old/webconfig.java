package top.hlfpersonal.web.ws.old;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-28 19:45
 **/
@Configuration
@EnableWebSocket
public class webconfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        ServerEndpointConfig
        registry.addHandler(getweb(),"/ws").setAllowedOrigins("*");
    }

    public WebSocketHandler getweb(){
        return new ws1();
    }
}
