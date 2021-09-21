package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wzy.crowd.entity.vo.*;
import wzy.crowd.service.ProjectPOService;
import wzy.crowd.utils.ResultSet;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/8-11:26
 */
@Controller
public class ProjectPOProviderHandler {

    @Autowired
    private ProjectPOService projectPOService;

    @PostMapping("save/mysql/projectVO")
    @ResponseBody
    public ResultSet saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId){
        try {
            projectPOService.saveProject(projectVO,memberId);
            return ResultSet.success();
        }catch (Exception e){
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("get/portal/projectInfo")
    @ResponseBody
    public ResultSet getPortalProjectInfo(){
        try {
            List<PortalTypeVO> portalTypeVO = projectPOService.getPortalTypeVO();
            return ResultSet.success().addData(portalTypeVO);
        }catch (Exception e){
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("remote/get/detail/projectInfo")
    @ResponseBody
    public ResultSet getDetailProjectInfo(@RequestParam("projectId")Integer projectId){
        try {
            DetailProjectVO detailProjectVO = projectPOService.getDetailProjectVOByProjectId(projectId);
            return ResultSet.success().addData(detailProjectVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }
}
