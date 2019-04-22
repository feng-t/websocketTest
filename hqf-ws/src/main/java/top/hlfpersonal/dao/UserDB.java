package top.hlfpersonal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.hlfpersonal.vo.TUser;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-03-27 20:43
 **/
public interface UserDB extends JpaRepository<TUser,String>, JpaSpecificationExecutor<TUser> {
    TUser findByUsername(String username);
}
