package top.hlfpersonal.web.ws.old;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-28 20:07
 **/
@Configuration
public class wsconfig extends ServerEndpointConfig.Configurator {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 将用户信息存储到socket的配置里
        sec.getUserProperties().put("user", SecurityUtils.getSubject().getPrincipal());
        super.modifyHandshake(sec, request, response);
    }
}