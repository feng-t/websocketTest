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
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import top.hlfpersonal.dao.UserDB;
import top.hlfpersonal.enums.SetStr;
import top.hlfpersonal.vo.TUser;

import java.util.Collection;
import java.util.Optional;

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
    @Autowired
    private SessionDAO sessionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        TUser db = userDB.findByUsername(username);
        if (db != null) {
            Collection<Session> sessions = sessionDAO.getActiveSessions();
            SecurityUtils.getSubject().getSession().setAttribute(SetStr.USER,db);
            /**
             * 单点登陆
             */
            for (Session session : sessions) {
                Optional<Object> optional = Optional.ofNullable(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                String attribute=optional.isPresent()?(String) ((SimplePrincipalCollection) optional.get()).getPrimaryPrincipal():"";
                if (attribute.equals(db.getId())) {
                    session.setAttribute(SetStr.ISLOGIN,db.getId());
                }
            }
            return new SimpleAuthenticationInfo(db.getId(), db.getPassword(), getName());
        } else {
            throw new AuthenticationException();
        }
    }
}
