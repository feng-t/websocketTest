package top.hlfpersonal.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hlfpersonal.dao.UserDB;
import top.hlfpersonal.service.UserService;
import top.hlfpersonal.util.IdWorker;
import top.hlfpersonal.vo.TUser;

/**
 * @program: hqf-parent
 * @description: UserService实现
 * @author: feng
 * @create: 2019-03-27 20:54
 **/
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDB userDB;

    @Autowired
    private IdWorker idWorker;

    @Override
    public TUser save(TUser user) {
        user.setId(idWorker.nextId());
        return userDB.save(user);
    }
}
