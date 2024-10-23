package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.entity.SwProjectItemExample;
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
public interface SwProjectItemMapper {
    @SelectProvider(type=SwProjectItemSqlProvider.class, method="countByExample")
    long countByExample(SwProjectItemExample example);

    @DeleteProvider(type=SwProjectItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwProjectItemExample example);

    @Delete({
        "delete from sw_project_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_project_item (project_id, module_type, ",
        "update_by, detail_type, ",
        "project_item, project_value, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{projectId,jdbcType=INTEGER}, #{moduleType,jdbcType=VARCHAR}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{detailType,jdbcType=VARCHAR}, ",
        "#{projectItem,jdbcType=VARCHAR}, #{projectValue,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwProjectItem record);

    @InsertProvider(type=SwProjectItemSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwProjectItem record);

    @SelectProvider(type=SwProjectItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_type", property="detailType", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_item", property="projectItem", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_value", property="projectValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProjectItem> selectByExampleWithRowbounds(SwProjectItemExample example, RowBounds rowBounds);

    @SelectProvider(type=SwProjectItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_type", property="detailType", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_item", property="projectItem", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_value", property="projectValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProjectItem> selectByExample(SwProjectItemExample example);

    @Select({
        "select",
        "id, project_id, module_type, update_by, detail_type, project_item, project_value, ",
        "is_delete, create_time, datetime_lastchange",
        "from sw_project_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project_id", property="projectId", jdbcType=JdbcType.INTEGER),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="detail_type", property="detailType", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_item", property="projectItem", jdbcType=JdbcType.VARCHAR),
        @Result(column="project_value", property="projectValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwProjectItem selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwProjectItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwProjectItem record, @Param("example") SwProjectItemExample example);

    @UpdateProvider(type=SwProjectItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwProjectItem record, @Param("example") SwProjectItemExample example);

    @UpdateProvider(type=SwProjectItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwProjectItem record);

    @Update({
        "update sw_project_item",
        "set project_id = #{projectId,jdbcType=INTEGER},",
          "module_type = #{moduleType,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "detail_type = #{detailType,jdbcType=VARCHAR},",
          "project_item = #{projectItem,jdbcType=VARCHAR},",
          "project_value = #{projectValue,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwProjectItem record);
}