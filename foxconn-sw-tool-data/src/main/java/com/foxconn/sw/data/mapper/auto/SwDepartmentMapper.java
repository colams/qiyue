package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwDepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwDepartmentMapper {
    @SelectProvider(type=SwDepartmentSqlProvider.class, method="countByExample")
    long countByExample(SwDepartmentExample example);

    @DeleteProvider(type=SwDepartmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwDepartmentExample example);

    @Delete({
        "delete from sw_department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_department (name, short_name, ",
        "manager_no, description, ",
        "parent_id, status, ",
        "start_date, end_date, ",
        "datetime_lastchange)",
        "values (#{name,jdbcType=VARCHAR}, #{shortName,jdbcType=VARCHAR}, ",
        "#{managerNo,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{startDate,jdbcType=VARCHAR}, #{endDate,jdbcType=VARCHAR}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDepartment record);

    @InsertProvider(type=SwDepartmentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwDepartment record);

    @SelectProvider(type=SwDepartmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="short_name", property="shortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_no", property="managerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDepartment> selectByExampleWithRowbounds(SwDepartmentExample example, RowBounds rowBounds);

    @SelectProvider(type=SwDepartmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="short_name", property="shortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_no", property="managerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDepartment> selectByExample(SwDepartmentExample example);

    @Select({
        "select",
        "id, name, short_name, manager_no, description, parent_id, status, start_date, ",
        "end_date, datetime_lastchange",
        "from sw_department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="short_name", property="shortName", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_no", property="managerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwDepartment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwDepartmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwDepartment record, @Param("example") SwDepartmentExample example);

    @UpdateProvider(type=SwDepartmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwDepartment record, @Param("example") SwDepartmentExample example);

    @UpdateProvider(type=SwDepartmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwDepartment record);

    @Update({
        "update sw_department",
        "set name = #{name,jdbcType=VARCHAR},",
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