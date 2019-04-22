package top.hlfpersonal.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;
import top.hlfpersonal.vo.TUser;

/**
 * @program: hqf-parent
 * @description: 登陆
 * @author: feng
 * @create: 2019-03-28 12:09
 **/
@Slf4j
public class UserRegisteAndLogin {

    public static <T> Result userLogin(TUser user) {
        if (user == null) {
            throw new RuntimeException("user is null");
        }
        Subject subject = SecurityUtils.getSubject();
        String toHex = new SimpleHash("MD5", user.getPassword(), user.getPassword(), 2).toHex();
        boolean authenticated = subject.isAuthenticated();
        if (authenticated) {
            return Result.ERROR_INFO(Status.USER_ALREADY_LOGIN, subject.getPrincipal());
        }
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), toHex);
        try {
            subject.login(token);
//            subject.getSession().setAttribute("login",(TUser)subject.getPrincipal());
        } catch (AuthenticationException e) {
            token.clear();
            return Result.ERROR(Status.USER_OR_PASSWORD_ERROR);
        }
        log.info("登陆成功--->{}",token);
        return Result.SUCCESS(Status.USER_LOGIN_SUCCESS, token);
    }

}
