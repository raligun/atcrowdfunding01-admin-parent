package wzy.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.AdminExample;
import wzy.crowd.entity.Role;
import wzy.crowd.exception.AccountExistException;
import wzy.crowd.exception.LoginFailedException;
import wzy.crowd.mapper.AdminMapper;
import wzy.crowd.service.AdminService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.CrowdUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author 网中鱼
 * @date 2021/8/25-23:07
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public int saveAdmin(Admin admin) {
        try {
            return adminMapper.insert(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException) {
            throw new AccountExistException("账户已经存在！","admin_add");
        }
            throw e;
        }
//        return 10/0;
    }

    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public Admin getAdminByAcct(String loginAcct, String userPswd) {
        Admin admin = getAdminByAcct(loginAcct);

        if (admin == null){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }
        String userPswdDB = admin.getUserPswd();
        String userPswdMd5 = CrowdUtil.md5(userPswd);

        if (!Objects.equals(userPswdDB,userPswdMd5)){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }

        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pn, Integer pageSize) {
        PageHelper.startPage(pn,pageSize);

        AdminExample example = new AdminExample();

        List<Admin> admins = adminMapper.selectAdminListByKeyword(keyword);

        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        return pageInfo;
    }

    @Override
    public int deleteAdmin(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAdmin(Admin admin) {
        try {
            return adminMapper.updateByPrimaryKeySelective(admin);
        }catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                throw new AccountExistException("账号已经存在！","system-error");
            }
            throw e;
        }

    }

    @Override
    public List<Role> getAssignRole(Integer adminId) {

        return adminMapper.getAssignRole(adminId);
    }
    @Override
    public List<Role> getUnAssignRole(Integer adminId) {

        return adminMapper.getUnAssignRole(adminId);
    }

    @Override
    public void saveNewAssign(Integer adminId,List<Integer> roleIds) {
        adminMapper.deleteOldAssignRole(adminId);
        if (roleIds != null && roleIds.size() > 0){

            adminMapper.insertNewAssignRole(adminId,roleIds);
        }
    }

    @Override
    public Admin getAdminByAcct(String loginAcct) {
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(example);


        if (admins==null || admins.size() == 0 ){
            throw new LoginFailedException(Constant.MESSAGE_LOGIN_FAILED);
        }
        if (admins.size() > 1){
            throw new LoginFailedException(Constant.MESSAGE_SYSTEM_ERROR);
        }
        Admin admin = admins.get(0);
        return admin;
    }
}
