package wzy.crowd.handler;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.Role;
import wzy.crowd.exception.AccountExistException;
import wzy.crowd.exception.IllegalFieldException;
import wzy.crowd.exception.LoginFailedException;
import wzy.crowd.service.AdminService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.CrowdUtil;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 网中鱼
 * @date 2021/8/26-23:27
 */
@Controller
public class AdminHandler {
    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("admin/roleAssign")
    public String roleAssign(@RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             @RequestParam(value = "keyword",defaultValue = "")String keyword,
                             @RequestParam("adminId")Integer adminId,
                             @RequestParam(value = "roleIds",required = false)List<Integer> roleIds){
        adminService.saveNewAssign(adminId,roleIds);

        return "redirect:/admin/user?pn=" + pn + "&keyword=" + keyword;
    }

    /**
     * 根据 id 获取管理员的权限信息
     * @param adminId
     * @param Model
     * @return
     */
    @RequestMapping("admin/assign")
    public String getAdminRoleMes(@RequestParam("id")Integer adminId,
                                    Model Model){
        List<Role> assignRole = adminService.getAssignRole(adminId);
        List<Role> unAssignRole = adminService.getUnAssignRole(adminId);

        Model.addAttribute("assignRole",assignRole);
        Model.addAttribute("unAssignRole",unAssignRole);
        Model.addAttribute("adminId",adminId);

        return "assignRole";
    }

    /**
     *处理登录的方法。后已经被spring security 代替
     */
//    @RequestMapping(value = "do/login")
//    public String login(@RequestParam("loginAcct") String loginAcct,
//                        @RequestParam("userPswd") String userPswd,
//                        HttpSession session) {
//        Admin admin = adminService.getAdminByAcct(loginAcct, userPswd);
//
//        session.setAttribute(Constant.ATTR_NAME_LOGIN_ADMIN, admin);
//
//        return "redirect:/admin_main";
//    }

    /**
     *  查找管理员信息到指定页面展示
     * @return
     */
    @RequestMapping("admin/user")
    public String toManagerUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                Model model) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pn, pageSize);
        model.addAttribute(Constant.ATTR_NAME_PAGEINFO, pageInfo);
        model.addAttribute("keyword", keyword);

        return "admin_user";
    }

    @RequestMapping("admin/delete")
    public String delAdmin(@RequestParam("id") Integer id,
                           @RequestParam("pn") Integer pn,
                           @RequestParam("keyword") String keyword) {
        int i = adminService.deleteAdmin(id);

        return "redirect:/admin/user?pn=" + pn + "&keyword=" + keyword;
    }

    @RequestMapping("admin/add")
    public String addAdmin(Admin admin) {

        if (admin == null || admin.getLoginAcct() == null || admin.getUserPswd() == null) {
            throw new IllegalFieldException("含有非法字段");
        }

        admin.setUserPswd(bCryptPasswordEncoder.encode(admin.getUserPswd()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String creatTime = dateFormat.format(new Date());
        admin.setCreateTime(creatTime);
        adminService.saveAdmin(admin);

        return "redirect:/admin/user?pn=" + Integer.MAX_VALUE;
    }

    @RequestMapping("admin/update")
    public String updateAdmin(Admin admin) {
        if (admin.getUserPswd()!=null){
            admin.setUserPswd(CrowdUtil.md5(admin.getUserPswd()));

        }
        String keyword = "";
        if (admin.getLoginAcct()!= null){
            keyword = admin.getLoginAcct();
        }
        adminService.updateAdmin(admin);

        return "redirect:/admin/user?keyword=" + keyword;
    }

    @RequestMapping("admin/to/update/{id}")
    public String toUpdate(@PathVariable("id") Integer id,
                           Model model) {
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("admin", admin);
        return "admin_edit";
    }
}
