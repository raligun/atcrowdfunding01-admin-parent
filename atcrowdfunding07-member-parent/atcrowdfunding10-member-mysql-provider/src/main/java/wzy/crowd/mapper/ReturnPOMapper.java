package wzy.crowd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wzy.crowd.entity.po.ReturnPO;
import wzy.crowd.entity.po.ReturnPOExample;


public interface ReturnPOMapper {
    long countByExample(ReturnPOExample example);

    int deleteByExample(ReturnPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReturnPO record);

    int insertSelective(ReturnPO record);

    List<ReturnPO> selectByExample(ReturnPOExample example);

    ReturnPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    int updateByExample(@Param("record") ReturnPO record, @Param("example") ReturnPOExample example);

    int updateByPrimaryKeySelective(ReturnPO record);

    int updateByPrimaryKey(ReturnPO record);

    int insertBatch(@Param("returnPOList") List<ReturnPO> returnPOList);
}