package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wzy.crowd.entity.Auth;
import wzy.crowd.service.AuthService;
import wzy.crowd.utils.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/31-11:05
 */
@RestController
public class AuthHandler {
    @Autowired
    private AuthService authService;

    @PostMapping("auth/getAllAuth")
    public ResultSet getAllAuth(){
        List<Auth> allAuth = authService.getAll();
        return ResultSet.success().addData(allAuth);
    }

    @RequestMapping("auth/getAssignAuth")
    public ResultSet getAssignAuth(@RequestParam("roleId")Integer roleId){
        List<Integer> authList = authService.getAssignAuthByRoleId(roleId);
        return ResultSet.success().addData(authList);
    }

    @PostMapping("auth/assignRoleNewAuth")
    public ResultSet assignRoleNewAuth(@RequestParam(value = "roleId",required = false)Integer roleId,
                                       @RequestParam(value = "auths",required = false)String auths){
        if (roleId== null){
            return ResultSet.error();
        }
        List<Integer> authlist = new ArrayList<>();
        if (auths!="" || auths != null){
            String[] split = auths.split(",");
            for (int i = 0; i < split.length; i++) {
                authlist.add(Integer.valueOf(split[i]));
            }
        }
        authService.assignRoleNewAuth(roleId,authlist);
        return ResultSet.success();
    }

}
