package top.hlfpersonal.web.ws;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hlfpersonal.dao.GroupRelaUserDB;
import top.hlfpersonal.enums.SetStr;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.vo.TMsg;
import top.hlfpersonal.vo.TUser;
import top.hlfpersonal.vo.UserTemp;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @program: hqf-parent
 * @description: 你没体验过的船新版本
 * @author: feng
 * @create: 2019-03-28 22:12
 **/
@Slf4j
@RestController
@RequestMapping("wss")
public class SpringWS {
    @Autowired
    private SessionDAO sessionDAO;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RedisTemplate redisTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String hello(String str) {
        return str;
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, TMsg msg) {
        log.info("发送中--》name--->{},msg----->{}", principal.getName(), msg.getTo());
        if (principal.getName().equals("1112270255367979008")){
            return;
        }
//        template.convertAndSendToUser(msg.getTo(),"/queue/sendtouser", Result.SUCCESS(Status.SENDMSG_SUCCESS,msg));
        template.convertAndSendToUser("1112270255367979008","/queue/sendtouser", Result.SUCCESS(Status.SENDMSG_SUCCESS,msg));
        log.info("发送成功--》name--->{},msg----->{}", principal.getName(), msg.getTo());
    }

    /**
     * 获取所有好友
     *
     * @return
     */
    @MessageMapping("get")
    @SendTo("/topic/getuser")
    public Result get() {
        List<UserTemp> list = new ArrayList<>();
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            Optional<TUser> optional = Optional.ofNullable((TUser) session.getAttribute(SetStr.USER));
            optional.ifPresent(tUser -> list.add(new UserTemp(tUser.getId(), tUser.getUsername(), tUser.getNickname())));
        }
        if (list.size() > 0) {
            return Result.SUCCESS(Status.OK, list);
        } else {
            return Result.ERROR(Status.NULLERROR);
        }
    }

}
