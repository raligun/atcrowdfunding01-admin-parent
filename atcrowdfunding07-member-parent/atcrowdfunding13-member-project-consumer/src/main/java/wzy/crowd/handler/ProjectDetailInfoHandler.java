package wzy.crowd.handler;

import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

/**
 * @author 网中鱼
 * @date 2021/9/9-15:33
 */
@Controller
public class ProjectDetailInfoHandler {

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @RequestMapping("get/detail/projectInfo")
    public String getProjectDetailInfo(@RequestParam("projectId")Integer projectId,
                                       Model model){
        try {
            ResultSet resultSet = mysqlRemoteService.getDetailProjectInfo(projectId);
            model.addAttribute(Constant.PORTAL_PROJECT_INFO,resultSet.getData());
        }catch (Exception e){
            model.addAttribute(Constant.MESSAGE,e.getMessage());
        }
        return "project-show-detail";
    }
}
