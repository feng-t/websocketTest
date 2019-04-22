package top.hlfpersonal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import top.hlfpersonal.vo.TGroupRelaUser;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-04-02 18:20
 **/
public interface GroupRelaUserDB extends JpaRepository<TGroupRelaUser,Integer> {
}
