package wzy.crowd.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.Role;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/25-23:06
 */
@Service
public interface AdminService {
    int saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByAcct(String loginAcct, String userPswd);

    PageInfo<Admin> getPageInfo(String keyword, Integer pn, Integer pageSize);

    int deleteAdmin(Integer id);

    Admin getAdminById(Integer id);

    int updateAdmin(Admin admin);

    List<Role> getAssignRole(Integer adminId);

    List<Role> getUnAssignRole(Integer adminId);

    void saveNewAssign(Integer adminId,List<Integer> roleIds);

    Admin getAdminByAcct(String LoginAcct);
}
