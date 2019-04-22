package top.hlfpersonal.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hlfpersonal.dao.GroupRelaUserDB;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.vo.TMsg;
import top.hlfpersonal.vo.UserTemp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: hqf-parent
 * @description: 你没体验过的船新版本
 * @author: feng
 * @create: 2019-03-28 22:12
 **/
@Slf4j
@RestController
@RequestMapping("ws")
public class SpringWS {
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private GroupRelaUserDB groupRelaUserDB;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String hello(String str) {
        return str;
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, TMsg msg) {
        log.info("name--->{},msg----->{}",principal.getName(),msg);

        template.convertAndSendToUser(msg.getTo(), "/queue/notifications", msg);
    }

//    @MessageMapping("/sendMSG")
//    public void sendMSG(TMsg msg) {
//        Collection<Session> sessions = sessionDAO.getActiveSessions();
//        for (Session session : sessions) {
//            TUser user = (TUser) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
//            if (msg.getTo().equals(user.getId())) {
//                template.convertAndSendToUser(user.getId(), "/topic/msg", msg);
//            }
//        }
////        点对点发消息
//    }


    @MessageMapping("get")
    @SendTo("/topic/getuser")
    public Result get() {
        List<UserTemp> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            try {
                UserTemp user= (UserTemp) session.getAttribute("USER");
                list.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (list.size() > 0) {
            return Result.SUCCESS(Status.OK, list);
        } else {
            return Result.ERROR(Status.NULLERROR);
        }
    }

}
