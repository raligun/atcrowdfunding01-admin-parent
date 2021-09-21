package wzy.crowd.handler;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wzy.crowd.entity.vo.LoginMemberVO;
import wzy.crowd.entity.vo.MemberConfirmInfoVO;
import wzy.crowd.entity.vo.ProjectVO;
import wzy.crowd.entity.vo.ReturnVO;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.CrowdUtil;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.netty.handler.codec.DateFormatter.format;


/**
 * @author 网中鱼
 * @date 2021/9/7-17:52
 */
@RestController
public class ProjectConsumerHandler {
    @Autowired
    private MysqlRemoteService mysqlRemoteService;


    // 生成项目信息并保存到 mysql 数据库
    @PostMapping("create/confirm")
    public ResultSet createConfirm(MemberConfirmInfoVO memberConfirmInfoVO,
                                   HttpSession session){
        if (memberConfirmInfoVO == null
                || memberConfirmInfoVO.getCardnum() == null
                || memberConfirmInfoVO.getPaynum() == null ){

            return ResultSet.error(Constant.MESSAGE_STRING_INVALIDATE);
        }
        // 从 session 中取出临时工程对象
        ProjectVO projectVO = (ProjectVO) session.getAttribute(Constant.TEMP_PROJECT_KEY);
        if (projectVO == null){
            return ResultSet.error(Constant.MESSAGE_DATA_MISSING);
        }
        projectVO.setMemberConfirmInfoVO(memberConfirmInfoVO);

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.ATTR_DATE_PATTERN);
        String date = dateFormat.format(new Date());
        projectVO.setCreatedate(date);
        // 获取登录对象，获取其 id；
        LoginMemberVO loginMember = (LoginMemberVO) session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        Integer memberId = loginMember.getMID();
        // 将临时工程的数据保存到 mysql 数据库
        ResultSet saveResultSet =
                mysqlRemoteService.saveProjectVORemote(projectVO,memberId);

        if (saveResultSet.getCode() != 200){
            return saveResultSet;
        }

        session.removeAttribute(Constant.TEMP_PROJECT_KEY);
        return ResultSet.success(Constant.MESSAGE_PROJECT_INFO_SAVE_SUCCESS);
    }

    @PostMapping("save/return")
    public ResultSet saveReturn(ReturnVO returnVO, HttpSession session){

        if (returnVO == null){
            return ResultSet.error(Constant.MESSAGE_STRING_INVALIDATE);
        }

        ProjectVO projectVO = (ProjectVO) session.getAttribute(Constant.TEMP_PROJECT_KEY);
        if (projectVO == null){
            return ResultSet.error(Constant.MESSAGE_DATA_MISSING);
        }

        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        if (returnVOList == null){
            returnVOList = new ArrayList<>();
            // ????
            projectVO.setReturnVOList(returnVOList);
        }

        returnVOList.add(returnVO);
        session.setAttribute(Constant.TEMP_PROJECT_KEY,projectVO);
        return ResultSet.success();
    }


    @PostMapping("/save/return/picture")
    public ResultSet saveReturnPicture(@RequestParam("returnPicture")MultipartFile returnPicture,
                                       HttpSession session){
        if (returnPicture == null || returnPicture.isEmpty()){
            return ResultSet.error(Constant.MESSAGE_FILE_UP_FAILED);
        }
        String path = Constant.PROJECT_IMAGE_PATH +
                session.getAttribute(Constant.TEMP_PROJECT_NAME) + "\\" +
                Constant.RETURN_PICTURE_PATH + "\\" +
                returnPicture.getOriginalFilename();
        String url = Constant.PROJECT_IMAGE_URL_PATH +
                session.getAttribute(Constant.TEMP_PROJECT_NAME) + "/" +
                Constant.RETURN_PICTURE_PATH + "/" +
                returnPicture.getOriginalFilename();

        
        File returnPictureFile = new File(path);

        try {
            CrowdUtil.mkdir(returnPictureFile);
            returnPicture.transferTo(returnPictureFile);
            return ResultSet.success().addData(url);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }


    @PostMapping("save/project/information")
    public ResultSet saveProjectInformation(
                        // 接受除了文件以外的其他普通数据
                        ProjectVO projectVO,
                        // 接受上传的头图
                        @RequestParam(value = "headerPicture",required = false) MultipartFile headerPicture,
                        // 接受上传的详情图
                        @RequestParam(value = "detailPictureList",required = false) List<MultipartFile> detailPictureList,
                        // 用来将信息保存到 redis
                        HttpSession session
                        ){
        if (projectVO == null
        || projectVO.getDay() == null
        || projectVO.getMoney() == null
        || projectVO.getProjectDescription() == null
        || projectVO.getProjectName() == null){
            return ResultSet.error(Constant.MESSAGE_DATA_INCOMPLETE);
        }

        if (headerPicture == null || headerPicture.isEmpty()){
            return ResultSet.error(Constant.MESSAGE_HEADER_PICTURE_IS_EMPTY);
        }

        if ( detailPictureList.size() == 1
                && detailPictureList.get(0).getOriginalFilename().isEmpty()){
            return ResultSet.error(Constant.MESSAGE_DETAIL_PICTURE_IS_EMPTY);
        }

        if (projectVO.getTagIdList() == null || projectVO.getTagIdList().size() == 0){
            return ResultSet.error(Constant.MESSAGE_TAG_IS_EMPTY);
        }

        if (projectVO.getTypeIdList() == null || projectVO.getTypeIdList().size() == 0){
            return ResultSet.error(Constant.MESSAGE_TYPE_IS_EMPTY);
        }

        // 拼出头图的存放路径
        String headerPicturePath =Constant.PROJECT_IMAGE_PATH +
                            projectVO.getProjectName() + "\\"
                            + Constant.HEADER_PICTURE_PATH+ "\\"
                            + headerPicture.getOriginalFilename();
        // 拼出头图的页面显示的url地址
        String headerPictureUrl =Constant.PROJECT_IMAGE_URL_PATH +
                            projectVO.getProjectName() + "/"
                            + Constant.HEADER_PICTURE_PATH+ "/"
                            + headerPicture.getOriginalFilename();

        File headFile = new File(headerPicturePath);
        try {
            // 创建路径,储存头图
            CrowdUtil.mkdir(headFile);
            headerPicture.transferTo(headFile);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }

        // 拼出细节图存放路径
        String detailPicturePath =Constant.PROJECT_IMAGE_PATH +
                            projectVO.getProjectName()+ "\\"
                            + Constant.DETAIL_PICTURE_PATH+ "\\";
        // 拼出细节图页面显示的url地址
        String detailPictureUrl =Constant.PROJECT_IMAGE_URL_PATH +
                            projectVO.getProjectName()+ "/"
                            + Constant.DETAIL_PICTURE_PATH+ "/";
        // 记录是否有细节图保存失败
        boolean haveFailed = false;
        //用来保存细节图的路径
        List<String> detailPicturePathList = new ArrayList<>();
        // 遍历细节图
        for (MultipartFile detailPicture :
                detailPictureList) {

            File detailFile = new File(detailPicturePath +
                                        detailPicture.getOriginalFilename());

            try {
                // 创建路径,储存细节图
                CrowdUtil.mkdir(detailFile);
                detailPicture.transferTo(detailFile);

                detailPicturePathList.add(detailPictureUrl +
                        detailPicture.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                haveFailed = true;
            }
        }
        // 添加头图和细节图路径
        projectVO.setHeaderPicturePath(headerPictureUrl);
        projectVO.setDetailPicturePathList(detailPicturePathList);
        // 保存临时信息在 Session（redis） 域中
        session.setAttribute(Constant.TEMP_PROJECT_KEY,projectVO);
        session.setAttribute(Constant.TEMP_PROJECT_NAME,projectVO.getProjectName());
        if (haveFailed){
            return ResultSet.success(Constant.MESSAGE_HAS_DETAIL_PICTURE_ERROR);
        }
        return ResultSet.success(Constant.MESSAGE_PROJECT_INFO_SAVE_SUCCESS);
    }

}
