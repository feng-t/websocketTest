package top.hlfpersonal.web.ws.old;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-28 19:49
 **/
@Slf4j
public class ws1 implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("连接--->{}", SecurityUtils.getSubject().getPrincipal());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("handleMessage-----------");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

        log.info("handleTransportError-------------");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

        log.info("afterConnectionClosed-----------");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
