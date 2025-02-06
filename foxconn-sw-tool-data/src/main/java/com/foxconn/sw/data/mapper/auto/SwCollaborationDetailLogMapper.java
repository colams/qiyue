package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
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
public interface SwCollaborationDetailLogMapper {
    long countByExample(SwCollaborationDetailLogExample example);

    int deleteByExample(SwCollaborationDetailLogExample example);

    @Delete({
        "delete from sw_collaboration_detail_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail_log (detail_id, row_index, ",
        "col_index, operator, ",
        "remark, create_time, ",
        "datetime_lastchange)",
        "values (#{detailId,jdbcType=BIGINT}, #{rowIndex,jdbcType=INTEGER}, ",
        "#{colIndex,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetailLog record);

    int insertSelective(SwCollaborationDetailLog record);

    List<SwCollaborationDetailLog> selectByExampleWithRowbounds(SwCollaborationDetailLogExample example, RowBounds rowBounds);

    List<SwCollaborationDetailLog> selectByExample(SwCollaborationDetailLogExample example);

    @Select({
        "select",
        "id, detail_id, row_index, col_index, operator, remark, create_time, datetime_lastchange",
        "from sw_collaboration_detail_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCollaborationDetailLogMapper.BaseResultMap")
    SwCollaborationDetailLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwCollaborationDetailLog record, @Param("example") SwCollaborationDetailLogExample example);

    int updateByExample(@Param("record") SwCollaborationDetailLog record, @Param("example") SwCollaborationDetailLogExample example);

    int updateByPrimaryKeySelective(SwCollaborationDetailLog record);

    @Update({
        "update sw_collaboration_detail_log",
        "set detail_id = #{detailId,jdbcType=BIGINT},",
          "row_index = #{rowIndex,jdbcType=INTEGER},",
          "col_index = #{colIndex,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetailLog record);
}