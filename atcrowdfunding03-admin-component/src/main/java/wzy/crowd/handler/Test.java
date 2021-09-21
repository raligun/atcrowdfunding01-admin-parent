package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wzy.crowd.entity.Admin;
import wzy.crowd.service.AdminService;
import wzy.crowd.service.RoleService;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/8/26-1:46
 */
@Controller
public class Test {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/ssm")
    public String toSSM(Model model){
//        String s = null;
//        System.out.println(s.trim());
//        if (1==1){
//
//            throw new RuntimeException("运行时错误");
//        }
        List<Admin> list = adminService.getAll();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping("test/arrays")
    @ResponseBody
    public List<Integer> array1(@RequestParam("array")List<Integer> array){

        return array;
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(){
        System.out.println("tesst2");

        return "array";
    }
}
