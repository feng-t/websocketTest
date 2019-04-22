package top.hlfpersonal.web.ws.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hlfpersonal.dao.GroupDB;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.vo.TMsg;
import top.hlfpersonal.web.ws.WebController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: hqf-parent，获取个人所在的群，将群id拼接成订阅地址。实现群聊。
 * @description: ws实现
 * @author: feng
 * @create: 2019-04-11 13:34
 **/
@Slf4j
@RestController
@RequestMapping("ws")
public class WsControllerimpl implements WebController {

    private List<TMsg> listMsg = new ArrayList<>();
    private List<TMsg> listUserMsg = new ArrayList<>();
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GroupDB groupDB;
    /**
     * 转发发送消息
     */
    @Autowired
    private SimpMessagingTemplate template;

    @Override
    @MessageMapping("notice")
    public Result notice() {
        return null;
    }

    @Override
    @MessageMapping("sendToUser")
    public Result sendToUser(TMsg msg) {
        template.convertAndSendToUser(msg.getTo(), "/queue/sendToUser", msg);
        listUserMsg.add(msg);
        redisTemplate.opsForValue().set(msg.getTo(), listUserMsg);
        return null;
    }

    @Override
    @MessageMapping("sendToGroup")
    public Result sendToGroup(TMsg msg) {
        template.convertAndSend("group/" + msg.getTo(), msg);
        listMsg.add(msg);
        redisTemplate.opsForValue().set("group/" + msg.getTo(), listMsg);
        return Result.SUCCESS(Status.OK, "发送成功");
    }

    @Override
    @GetMapping("getFriends")
    public Result getFriends() {

        return null;
    }


    @Override
    @GetMapping("getGroups")
    public Result getGroups() {
        return null;
    }
}
