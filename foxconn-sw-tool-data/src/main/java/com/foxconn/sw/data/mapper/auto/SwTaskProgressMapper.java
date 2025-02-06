package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.entity.SwTaskProgressExample;
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
public interface SwTaskProgressMapper {
    long countByExample(SwTaskProgressExample example);

    int deleteByExample(SwTaskProgressExample example);

    @Delete({
        "delete from sw_task_progress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_task_progress (operate_type, task_id, ",
        "operate_eid, resource_ids, ",
        "progress, content, ",
        "datetime_lastchange)",
        "values (#{operateType,jdbcType=VARCHAR}, #{taskId,jdbcType=INTEGER}, ",
        "#{operateEid,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{progress,jdbcType=INTEGER}, #{content,jdbcType=VARCHAR}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTaskProgress record);

    int insertSelective(SwTaskProgress record);

    List<SwTaskProgress> selectByExampleWithRowbounds(SwTaskProgressExample example, RowBounds rowBounds);

    List<SwTaskProgress> selectByExample(SwTaskProgressExample example);

    @Select({
        "select",
        "id, operate_type, task_id, operate_eid, resource_ids, progress, content, datetime_lastchange",
        "from sw_task_progress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwTaskProgressMapper.BaseResultMap")
    SwTaskProgress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTaskProgress record, @Param("example") SwTaskProgressExample example);

    int updateByExample(@Param("record") SwTaskProgress record, @Param("example") SwTaskProgressExample example);

    int updateByPrimaryKeySelective(SwTaskProgress record);

    @Update({
        "update sw_task_progress",
        "set operate_type = #{operateType,jdbcType=VARCHAR},",
          "task_id = #{taskId,jdbcType=INTEGER},",
          "operate_eid = #{operateEid,jdbcType=VARCHAR},",
          "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "progress = #{progress,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTaskProgress record);
}