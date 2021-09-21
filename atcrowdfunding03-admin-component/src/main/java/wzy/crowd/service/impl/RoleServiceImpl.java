package wzy.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Auth;
import wzy.crowd.entity.Role;
import wzy.crowd.entity.RoleExample;
import wzy.crowd.mapper.AuthMapper;
import wzy.crowd.mapper.RoleMapper;
import wzy.crowd.service.RoleService;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/28-14:56
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;


    @Override
    public int saveRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public PageInfo<Role> getPageInfo(Integer pn,Integer pageSize,String keyword) {
        PageHelper.startPage(pn,pageSize);

        List<Role> roles = roleMapper.selectRoleByKeyword(keyword);

        return new PageInfo<>(roles);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int deleteRoles(List<Integer> idList) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andIdIn(idList);

        return roleMapper.deleteByExample(roleExample);
    }

    @Override
    public int deleteRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> getRoleByAdminId(Integer id) {
        return roleMapper.selectRoleByAdminId(id);
    }
}
