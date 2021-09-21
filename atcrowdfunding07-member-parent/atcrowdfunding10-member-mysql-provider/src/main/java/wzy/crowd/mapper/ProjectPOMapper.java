package wzy.crowd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wzy.crowd.entity.po.ProjectPO;
import wzy.crowd.entity.po.ProjectPOExample;
import wzy.crowd.entity.vo.DetailProjectVO;
import wzy.crowd.entity.vo.DetailReturnVO;
import wzy.crowd.entity.vo.PortalProjectVO;
import wzy.crowd.entity.vo.PortalTypeVO;

@Mapper
public interface ProjectPOMapper {
    long countByExample(ProjectPOExample example);

    int deleteByExample(ProjectPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectPO record);

    int insertSelective(ProjectPO record);

    List<ProjectPO> selectByExample(ProjectPOExample example);

    ProjectPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByExample(@Param("record") ProjectPO record, @Param("example") ProjectPOExample example);

    int updateByPrimaryKeySelective(ProjectPO record);

    int updateByPrimaryKey(ProjectPO record);

    int insertTagIdBatch(@Param("projectId") Integer projectId,
                          @Param("tagIdList") List<Integer> tagIdList);

    int insertTypeIdBatch(@Param("projectId") Integer projectId,
                          @Param("typeIdList") List<Integer> typeIdList);

    List<PortalTypeVO> selectPortalTypeVO();

    List<PortalProjectVO> selectPortalProjectVO();

    List<String> selectDetailPicturePath(Integer projectId);

    List<DetailReturnVO> selectDetailReturnVO(Integer projectId);

    DetailProjectVO selectDetailProjectVO(Integer projectId);
}