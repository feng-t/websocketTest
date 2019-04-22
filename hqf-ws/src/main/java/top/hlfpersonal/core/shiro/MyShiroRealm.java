package top.hlfpersonal.core.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import top.hlfpersonal.dao.UserDB;
import top.hlfpersonal.vo.TUser;
import top.hlfpersonal.vo.UserTemp;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-26 21:00
 **/
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserDB userDB;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        TUser db = userDB.findByUsername(username);
        if (db != null) {
            UserTemp temp = new UserTemp(db.getId(), db.getUsername(), db.getNickname());
            SecurityUtils.getSubject().getSession().setAttribute("USER",temp);
            return new SimpleAuthenticationInfo(db.getId(), db.getPassword(), getName());
        } else {
            throw new AuthenticationException();
        }
    }
}
