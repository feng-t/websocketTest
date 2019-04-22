package top.hlfpersonal.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import top.hlfpersonal.enums.SetStr;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.vo.TUser;

import java.util.Collection;
import java.util.Optional;

/**
 * @program: hqf-parent
 * @description: 登陆
 * @author: feng
 * @create: 2019-03-28 12:09
 **/
@Slf4j
public class UserRegisteAndLogin {
    public static <T> Result userLogin(TUser user, SessionDAO sessionDAO){
        return UserRegisteAndLogin.userLogin(user,sessionDAO,null);
    }
    public static <T> Result userLogin(TUser user, SessionDAO sessionDAO, Callback callback) {
        if (user == null) {
            throw new RuntimeException("user is null");
        }
        Subject subject = SecurityUtils.getSubject();
        String toHex = new SimpleHash("MD5", user.getPassword(), user.getPassword(), 2).toHex();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
//            subject.getSession().setTimeout(0);
            log.info("已经登陆------");
            return Result.ERROR_INFO(Status.USER_ALREADY_LOGIN, subject.getPrincipal());
        }


        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), toHex);
        try {
            subject.login(token);
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            String attr = subject.getSession().getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString();
            for (Session session : sessions) {
                Optional<Object> islogin = Optional.ofNullable(session.getAttribute(SetStr.ISLOGIN));
                if (islogin.orElse("").equals(attr)) {
                    if (callback!=null){
                        callback.ca(attr);
                    }
                    sessionDAO.delete(session);
                }
            }

        } catch (AuthenticationException e) {
            token.clear();
            return Result.ERROR(Status.USER_OR_PASSWORD_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.SUCCESS(Status.USER_LOGIN_SUCCESS, token);
    }

    public interface Callback{
        void ca(String user);
    }

}
