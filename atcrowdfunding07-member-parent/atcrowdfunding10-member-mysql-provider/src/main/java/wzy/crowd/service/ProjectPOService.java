package wzy.crowd.service;

import org.springframework.stereotype.Service;
import wzy.crowd.entity.vo.DetailProjectVO;
import wzy.crowd.entity.vo.PortalTypeVO;
import wzy.crowd.entity.vo.ProjectVO;

import java.text.ParseException;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/8-11:23
 */
@Service
public interface ProjectPOService {

    void saveProject(ProjectVO projectVO,Integer memberId) throws Exception;

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVOByProjectId(Integer projectId) throws ParseException;
}
