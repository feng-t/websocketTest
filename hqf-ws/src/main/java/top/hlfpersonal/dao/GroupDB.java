package top.hlfpersonal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.hlfpersonal.vo.TGroup;

/**
 * @program: hqf-parent
 * @description: 群资料
 * @author: feng
 * @create: 2019-03-27 20:51
 **/
public interface GroupDB extends JpaRepository<TGroup,Integer>,JpaSpecificationExecutor<TGroup>  {
}
