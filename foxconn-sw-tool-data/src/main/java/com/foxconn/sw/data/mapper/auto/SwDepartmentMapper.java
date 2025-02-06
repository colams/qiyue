package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwDepartmentMapper {
    int deleteByExample(SwDepartmentExample example);

    @Delete({
        "delete from sw_department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_department (level, name, ",
        "short_name, manager_no, ",
        "description, parent_id, ",
        "status, start_date, ",
        "end_date, datetime_lastchange)",
        "values (#{level,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{shortName,jdbcType=VARCHAR}, #{managerNo,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{parentId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDepartment record);

    int insertSelective(SwDepartment record);

    List<SwDepartment> selectByExampleWithRowbounds(SwDepartmentExample example, RowBounds rowBounds);

    List<SwDepartment> selectByExample(SwDepartmentExample example);

    @Select({
        "select",
        "id, level, name, short_name, manager_no, description, parent_id, status, start_date, ",
        "end_date, datetime_lastchange",
        "from sw_department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwDepartmentMapper.BaseResultMap")
    SwDepartment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwDepartment record, @Param("example") SwDepartmentExample example);

    int updateByExample(@Param("record") SwDepartment record, @Param("example") SwDepartmentExample example);

    int updateByPrimaryKeySelective(SwDepartment record);

    @Update({
        "update sw_department",
        "set level = #{level,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "short_name = #{shortName,jdbcType=VARCHAR},",
          "manager_no = #{managerNo,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "start_date = #{startDate,jdbcType=VARCHAR},",
          "end_date = #{endDate,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDepartment record);
}