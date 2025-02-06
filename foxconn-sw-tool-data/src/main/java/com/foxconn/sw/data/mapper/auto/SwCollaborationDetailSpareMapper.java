package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpareExample;
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
public interface SwCollaborationDetailSpareMapper {
    long countByExample(SwCollaborationDetailSpareExample example);

    int deleteByExample(SwCollaborationDetailSpareExample example);

    @Delete({
        "delete from sw_collaboration_detail_spare",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail_spare (task_id, detail_id, ",
        "operator, value, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{detailId,jdbcType=BIGINT}, ",
        "#{operator,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetailSpare record);

    int insertSelective(SwCollaborationDetailSpare record);

    List<SwCollaborationDetailSpare> selectByExampleWithRowbounds(SwCollaborationDetailSpareExample example, RowBounds rowBounds);

    List<SwCollaborationDetailSpare> selectByExample(SwCollaborationDetailSpareExample example);

    @Select({
        "select",
        "id, task_id, detail_id, operator, value, is_delete, create_time, datetime_lastchange",
        "from sw_collaboration_detail_spare",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCollaborationDetailSpareMapper.BaseResultMap")
    SwCollaborationDetailSpare selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwCollaborationDetailSpare record, @Param("example") SwCollaborationDetailSpareExample example);

    int updateByExample(@Param("record") SwCollaborationDetailSpare record, @Param("example") SwCollaborationDetailSpareExample example);

    int updateByPrimaryKeySelective(SwCollaborationDetailSpare record);

    @Update({
        "update sw_collaboration_detail_spare",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "detail_id = #{detailId,jdbcType=BIGINT},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetailSpare record);
}