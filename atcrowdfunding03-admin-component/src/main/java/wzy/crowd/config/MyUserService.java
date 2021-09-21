package wzy.crowd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.SecurityAdmin;
import wzy.crowd.mapper.AdminMapper;
import wzy.crowd.service.AdminService;
import wzy.crowd.service.AuthService;
import wzy.crowd.service.RoleService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/1-18:10
 */
@Component
public class MyUserService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    /**
     * Spring security 处理用户登录的请求，并确认其权限和角色
     * @param loginAcct
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginAcct) throws UsernameNotFoundException {
        // 查询数据库获取用户信息，没找到则抛出异常
        Admin admin = adminService.getAdminByAcct(loginAcct);

        Integer adminId = admin.getId();
        // 根据id获取用户角色，并封装进spring security
        List<String> roleList = roleService.getRoleByAdminId(adminId);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(adminId);
        for (String roleName :
                roleList) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }

        // 查询用户权限
        List<String> authList = authService.getAuthByAdminId(adminId);
        // 封装进spring security
        for (String auth :
                authList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth));
        }

        SecurityAdmin securityAdmin = new SecurityAdmin(admin, grantedAuthorities);

        return securityAdmin;
    }
}
