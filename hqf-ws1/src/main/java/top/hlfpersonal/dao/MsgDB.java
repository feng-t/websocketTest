package top.hlfpersonal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.hlfpersonal.vo.TMsg;

/**
 * @program: hqf-parent
 * @description: 消息
 * @author: feng
 * @create: 2019-03-27 20:48
 **/
public interface MsgDB extends JpaRepository<TMsg,Integer>, JpaSpecificationExecutor<TMsg> {
}
