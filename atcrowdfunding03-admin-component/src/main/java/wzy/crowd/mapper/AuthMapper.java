package wzy.crowd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wzy.crowd.entity.Auth;
import wzy.crowd.entity.AuthExample;

@Mapper
public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> selectAssignAuth(Integer roleId);

    int insertNewAuth(@Param("roleId") Integer roleId, @Param("auths") List<Integer> auths);

    void deleteOldAuth(Integer roleId);

    List<String> selectAuthByAdminId(Integer adminId);
}
