package wzy.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Auth;
import wzy.crowd.mapper.AuthMapper;
import wzy.crowd.service.AuthService;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/31-11:04
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(null);
    }

    @Override
    public List<Integer> getAssignAuthByRoleId(Integer roleId) {

        return authMapper.selectAssignAuth(roleId);
    }

    @Override
    public int assignRoleNewAuth(Integer roleId, List<Integer> auths) {
        authMapper.deleteOldAuth(roleId);

        if (auths != null && auths.size() >0 ){
            return authMapper.insertNewAuth(roleId,auths);
        }
        return 0;
    }

    @Override
    public List<String> getAuthByAdminId(Integer adminId) {

        return authMapper.selectAuthByAdminId(adminId);
    }

}
