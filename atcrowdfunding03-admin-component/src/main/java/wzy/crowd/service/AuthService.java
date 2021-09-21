package wzy.crowd.service;

import org.springframework.stereotype.Service;
import wzy.crowd.entity.Auth;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/31-11:03
 */
@Service
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignAuthByRoleId(Integer roleId);

    int assignRoleNewAuth(Integer roleId, List<Integer> auths);

    List<String> getAuthByAdminId(Integer adminId);
}
