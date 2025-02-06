package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCapexSetExample;
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
public interface SwCapexSetMapper {
    long countByExample(SwCapexSetExample example);

    int deleteByExample(SwCapexSetExample example);

    @Delete({
        "delete from sw_capex_set",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_capex_set (task_id, sheet_name, ",
        "type, number, set_value, ",
        "set_value_json, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{sheetName,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{number,jdbcType=INTEGER}, #{setValue,jdbcType=VARCHAR}, ",
        "#{setValueJson,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCapexSet record);

    int insertSelective(SwCapexSet record);

    List<SwCapexSet> selectByExampleWithRowbounds(SwCapexSetExample example, RowBounds rowBounds);

    List<SwCapexSet> selectByExample(SwCapexSetExample example);

    @Select({
        "select",
        "id, task_id, sheet_name, type, number, set_value, set_value_json, is_delete, ",
        "create_time, datetime_lastchange",
        "from sw_capex_set",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCapexSetMapper.BaseResultMap")
    SwCapexSet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwCapexSet record, @Param("example") SwCapexSetExample example);

    int updateByExample(@Param("record") SwCapexSet record, @Param("example") SwCapexSetExample example);

    int updateByPrimaryKeySelective(SwCapexSet record);

    @Update({
        "update sw_capex_set",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "sheet_name = #{sheetName,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "number = #{number,jdbcType=INTEGER},",
          "set_value = #{setValue,jdbcType=VARCHAR},",
          "set_value_json = #{setValueJson,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCapexSet record);
}