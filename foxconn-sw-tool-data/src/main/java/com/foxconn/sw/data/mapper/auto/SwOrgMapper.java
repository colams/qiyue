package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwOrg;
import com.foxconn.sw.data.entity.SwOrgExample;
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
public interface SwOrgMapper {
    @SelectProvider(type=SwOrgSqlProvider.class, method="countByExample")
    long countByExample(SwOrgExample example);

    @DeleteProvider(type=SwOrgSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwOrgExample example);

    @Delete({
        "delete from sw_org",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_org (name, description, ",
        "parent_id, status, ",
        "start_date, end_date, ",
        "datetime_lastchange)",
        "values (#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{startDate,jdbcType=TIMESTAMP}, #{endDate,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwOrg record);

    @InsertProvider(type=SwOrgSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwOrg record);

    @SelectProvider(type=SwOrgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwOrg> selectByExampleWithRowbounds(SwOrgExample example, RowBounds rowBounds);

    @SelectProvider(type=SwOrgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwOrg> selectByExample(SwOrgExample example);

    @Select({
        "select",
        "id, name, description, parent_id, status, start_date, end_date, datetime_lastchange",
        "from sw_org",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="start_date", property="startDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="end_date", property="endDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwOrg selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwOrgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwOrg record, @Param("example") SwOrgExample example);

    @UpdateProvider(type=SwOrgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwOrg record, @Param("example") SwOrgExample example);

    @UpdateProvider(type=SwOrgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwOrg record);

    @Update({
        "update sw_org",
        "set name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "start_date = #{startDate,jdbcType=TIMESTAMP},",
          "end_date = #{endDate,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwOrg record);
}