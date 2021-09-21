package wzy.crowd.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Auth;
import wzy.crowd.entity.Role;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/28-14:55
 */
@Service
public interface RoleService {

    int saveRole(Role role);

    List<Role> getAll();

    PageInfo<Role> getPageInfo(Integer pn,Integer pageSize,String keyword);

    int update(Role role);

    int deleteRoles(List<Integer> idList);

    int deleteRole(Integer id);


    List<String> getRoleByAdminId(Integer id);
}
