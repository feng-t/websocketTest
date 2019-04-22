package top.hlfpersonal.web.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import top.hlfpersonal.base.BaseController;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.service.UserService;
import top.hlfpersonal.utils.UserRegisteAndLogin;
import top.hlfpersonal.vo.TUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * @program: hqf-parent
 * @description: 用户界面
 * @author: feng
 * @create: 2019-03-27 22:10
 **/
@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private UserService userService;
    @Autowired
    private SessionDAO sessionDAO;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping(value = "singup")
    public Result singup(@RequestBody TUser user) {
        if (user.getNickname() == null) {
            user.setNickname("user" + UUID.randomUUID().toString().substring(0, 6).replace("-", ""));
        }
        TUser save = null;
        String sp = user.getPassword();
        try {
            String password = new SimpleHash("MD5", user.getPassword(), user.getPassword(), 2).toHex();
            user.setPassword(password);
            save = userService.save(user);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return Result.ERROR(Status.USER_ALREADY_SINGUP);
            }
        }
        if (save == null) {
            return Result.ERROR(Status.USER_ALREADY_SINGUP);
        }
        user.setPassword(sp);
        Result result = UserRegisteAndLogin.userLogin(user,sessionDAO);
        log.info("登陆情况->{}", result);
        user.setPassword(null);
        return Result.SUCCESS(Status.USER_SINGUP_CUCCESS, user);
    }

    @PostMapping("singin")
    public Result singin(@RequestBody TUser user) {
        return UserRegisteAndLogin.userLogin(user,sessionDAO,(u)->{
            template.convertAndSendToUser(u,"/queue/sendtouser",Result.ERROR(Status.USER_OFFSITE_LOGOUT));
        });
    }

    @GetMapping("out")
    public Result out() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().logout();
        }
        return Result.SUCCESS(Status.USER_LOGOUT, null);
    }
    @GetMapping(value = "ss")
    @ResponseBody
    public Result ss() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        ArrayList<TUser> users = new ArrayList<>();
        for (Session session : sessions) {
            TUser login = (TUser) session.getAttribute("login");
            login.setPassword(null);
            users.add(login);
        }
        return Result.SUCCESS(Status.OK,users);
    }




}
