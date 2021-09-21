package wzy.crowd.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wzy.crowd.entity.Admin;
import wzy.crowd.entity.AdminExample;
import wzy.crowd.entity.Role;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<Admin> selectAdminListByKeyword(String keyword);

    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Role> getAssignRole(Integer adminId);

    List<Role> getUnAssignRole(Integer adminId);

    int deleteOldAssignRole(@Param("adminId") Integer adminId);

    int insertNewAssignRole(@Param("adminId") Integer adminId,@Param("roleIds") List<Integer> roleIds);
}