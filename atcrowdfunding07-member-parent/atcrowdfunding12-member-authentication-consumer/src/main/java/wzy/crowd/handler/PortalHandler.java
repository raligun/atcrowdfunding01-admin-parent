package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

/**
 * @author 网中鱼
 * @date 2021/9/5-18:05
 */
@Controller
public class PortalHandler {
    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @RequestMapping("/")
    public String getProjectInfoToIndex(Model model){
        ResultSet resultSet = null;
        try {
            resultSet = mysqlRemoteService.getPortalProjectInfo();
            model.addAttribute(Constant.PORTAL_PROJECT_INFO,resultSet.getData());
        }catch (Exception e){
            e.printStackTrace();
            resultSet.setMessage(e.getMessage());
        }
        model.addAttribute("resultSet",resultSet);
        return "index";
    }
}
