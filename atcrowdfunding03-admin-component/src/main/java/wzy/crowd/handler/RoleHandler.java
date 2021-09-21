package wzy.crowd.handler;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wzy.crowd.entity.Auth;
import wzy.crowd.entity.Role;
import wzy.crowd.service.RoleService;
import wzy.crowd.utils.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/28-16:22
 */
@RestController
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @Secured({"ROLE_网中鱼"})
    @RequestMapping("role/pageInfo")
    public ResultSet getPageInfo(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 @RequestParam(value = "keyword",defaultValue = "") String keyword){
        PageInfo<Role> pageInfo = roleService.getPageInfo(pn, pageSize, keyword);
        return ResultSet.success().addData(pageInfo);
    }

    @PreAuthorize("hasAuthority('role:add')")
    @RequestMapping("role/add")
    public ResultSet addRole(Role role){
        roleService.saveRole(role);
        return ResultSet.success();
    }


    @RequestMapping("role/update")
    public ResultSet updateRole(Role role){
        roleService.update(role);
        return ResultSet.success();
    }


    @RequestMapping("role/delete")
    public ResultSet deleteRole(@RequestParam("ids")String ids){
        if (ids == null){
            return ResultSet.error();
        }

        if (!ids.contains(",")){
            roleService.deleteRole(Integer.valueOf(ids));
            return ResultSet.success();
        }

        String[] split = ids.split(",");

        List<Integer> idList = new ArrayList<>();

        for (String id :
                split) {
            idList.add(Integer.valueOf(id));
        }
        roleService.deleteRoles(idList);
        return ResultSet.success();
    }
}
