package wzy.crowd.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wzy.crowd.entity.po.MemberConfirmInfoPO;
import wzy.crowd.entity.po.MemberLaunchInfoPO;
import wzy.crowd.entity.po.ProjectPO;
import wzy.crowd.entity.po.ReturnPO;
import wzy.crowd.entity.vo.*;
import wzy.crowd.mapper.*;
import wzy.crowd.service.ProjectPOService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.CrowdUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/8-11:24
 */
@Service
@Transactional(readOnly = true)
public class ProjectPOServiceImpl implements ProjectPOService {
    @Autowired
    private ProjectPOMapper projectPOMapper;

    @Autowired
    private MemberConfirmInfoPOMapper memberConfirmInfoPOMapper;

    @Autowired
    private MemberLaunchInfoPOMapper memberLaunchInfoPOMapper;

    @Autowired
    private ProjectItemPicPOMapper projectItemPicPOMapper;

    @Autowired
    private ReturnPOMapper returnPOMapper;


    @Transactional(readOnly = false,rollbackFor = Exception.class,
            propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveProject(ProjectVO projectVO,Integer memberId) throws Exception {

        // 保存 projectPO
        ProjectPO projectPO = new ProjectPO();
        BeanUtils.copyProperties(projectVO,projectPO);

        // 设置登录人的 id 信息
        projectPO.setMemberid(memberId);

        // 设置项目截止时间

        String deploydate = CrowdUtil.addDate(projectVO.getCreatedate(), projectVO.getDay());
        projectPO.setDeploydate(deploydate);
        projectPOMapper.insertSelective(projectPO);


        Integer projectId = projectPO.getId();

        // 保存 MemberConfirmInfoPO
        MemberConfirmInfoVO memberConfirmInfoVO = projectVO.getMemberConfirmInfoVO();
        MemberConfirmInfoPO memberConfirmInfoPO = new MemberConfirmInfoPO();
        BeanUtils.copyProperties(memberConfirmInfoVO,memberConfirmInfoPO);
        memberConfirmInfoPO.setMemberid(memberId);
        memberConfirmInfoPOMapper.insertSelective(memberConfirmInfoPO);


        // 保存 MemberLaunchInfoPO
        MemberLauchInfoVO memberLauchInfoVO = projectVO.getMemberLauchInfoVO();
        MemberLaunchInfoPO memberLaunchInfoPO = new MemberLaunchInfoPO();
        BeanUtils.copyProperties(memberLauchInfoVO,memberLaunchInfoPO);
        memberLaunchInfoPO.setMemberid(memberId);
        memberLaunchInfoPOMapper.insertSelective(memberLaunchInfoPO);


        // 保存 ProjectItemPicPO
        List<String> detailPicturePathList = projectVO.getDetailPicturePathList();
        projectItemPicPOMapper.insertBatch(projectId, detailPicturePathList);


        // 保存 ReturnPO
        List<ReturnVO> returnVOList = projectVO.getReturnVOList();
        ReturnPO returnPO = new ReturnPO();
        List<ReturnPO> returnPOList = new ArrayList<>();
        for (ReturnVO returnVO :
                returnVOList) {
            BeanUtils.copyProperties(returnVO,returnPO);
            returnPO.setProjectid(projectId);
            returnPOList.add(returnPO);
        }
        returnPOMapper.insertBatch(returnPOList);


        // 保存 TagPO
        List<Integer> tagIdList = projectVO.getTagIdList();
        projectPOMapper.insertTagIdBatch(projectId, tagIdList);


        // 保存 TypePO
        List<Integer> typeIdList = projectVO.getTypeIdList();
        projectPOMapper.insertTypeIdBatch(projectId, typeIdList);
    }

    @Override
    public List<PortalTypeVO> getPortalTypeVO() {
        return projectPOMapper.selectPortalTypeVO();
    }

    @Override
    public DetailProjectVO getDetailProjectVOByProjectId(Integer projectId) throws ParseException {

        DetailProjectVO detailProjectVO = projectPOMapper.selectDetailProjectVO(projectId);

        String deployDate = detailProjectVO.getDeployDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.ATTR_DATE_PATTERN);
        long start = new Date().getTime();
        long end = dateFormat.parse(deployDate).getTime();
        int lastDay = (int) ((end-start) / 1000 / 60 / 60 /24);

        detailProjectVO.setLastDay(lastDay);

        return detailProjectVO;
    }

}
