package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import wzy.crowd.entity.Menu;
import wzy.crowd.service.MenuService;
import wzy.crowd.utils.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 网中鱼
 * @date 2021/8/29-20:46
 */
@RestController
public class MenuHandler {

    @Autowired
    private MenuService menuService;

    @RequestMapping("menu/get")
    public ResultSet getMenu(){
        List<Menu> menus = menuService.getAll();
        Map<Integer,Menu> menuMap = new HashMap<>();
        Menu root = null;
        for (Menu menu :
                menus) {
            menuMap.put(menu.getId(),menu);
        }
        for (Menu menu :
                menus) {
            Integer pid = menu.getPid();
            if (pid == null){
                root = menu;
                continue;
            }
            Menu parent = menuMap.get(pid);
            parent.addChild(menu);
        }

        return ResultSet.success().addData(root);
    }

    @RequestMapping("menu/add")
    public ResultSet addMenu(Menu menu){

        if (menu.getPid()==null || menu.getName() == null){
            return ResultSet.error();
        }

        int i = menuService.addMenu(menu);

        if (i<1){
            return ResultSet.error();
        }

        return ResultSet.success();
    }

    @RequestMapping("menu/update")
    public ResultSet updateMenu(Menu menu){


        int i = menuService.updateMenu(menu);

        if (i<1){
            return ResultSet.error();
        }

        return ResultSet.success();
    }

    @RequestMapping("menu/delete")
    public ResultSet deleteMenu(@RequestParam("id")Integer id){


        int i = menuService.deleteMenu(id);

        if (i<1){
            return ResultSet.error();
        }

        return ResultSet.success();
    }

}
