package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailExample;
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
public interface SwCollaborationDetailMapper {
    long countByExample(SwCollaborationDetailExample example);

    int deleteByExample(SwCollaborationDetailExample example);

    @Delete({
        "delete from sw_collaboration_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail (scu_id, row_index, ",
        "col_index, item, ",
        "item_value, create_time, ",
        "datetime_lastchange, spare_value)",
        "values (#{scuId,jdbcType=BIGINT}, #{rowIndex,jdbcType=INTEGER}, ",
        "#{colIndex,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
        "#{itemValue,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{spareValue,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetail record);

    int insertSelective(SwCollaborationDetail record);

    List<SwCollaborationDetail> selectByExampleWithRowbounds(SwCollaborationDetailExample example, RowBounds rowBounds);

    List<SwCollaborationDetail> selectByExample(SwCollaborationDetailExample example);

    @Select({
        "select",
        "id, scu_id, row_index, col_index, item, item_value, create_time, datetime_lastchange, ",
        "spare_value",
        "from sw_collaboration_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCollaborationDetailMapper.BaseResultMap")
    SwCollaborationDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SwCollaborationDetail record, @Param("example") SwCollaborationDetailExample example);

    int updateByExample(@Param("record") SwCollaborationDetail record, @Param("example") SwCollaborationDetailExample example);

    int updateByPrimaryKeySelective(SwCollaborationDetail record);

    @Update({
        "update sw_collaboration_detail",
        "set scu_id = #{scuId,jdbcType=BIGINT},",
          "row_index = #{rowIndex,jdbcType=INTEGER},",
          "col_index = #{colIndex,jdbcType=INTEGER},",
          "item = #{item,jdbcType=VARCHAR},",
          "item_value = #{itemValue,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "spare_value = #{spareValue,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetail record);
}