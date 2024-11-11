package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCapexSetExample;
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
public interface SwCapexSetMapper {
    @SelectProvider(type=SwCapexSetSqlProvider.class, method="countByExample")
    long countByExample(SwCapexSetExample example);

    @DeleteProvider(type=SwCapexSetSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCapexSetExample example);

    @Delete({
        "delete from sw_capex_set",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_capex_set (task_id, sheet_name, ",
        "type, number, set_value, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{sheetName,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{number,jdbcType=INTEGER}, #{setValue,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCapexSet record);

    @InsertProvider(type=SwCapexSetSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCapexSet record);

    @SelectProvider(type=SwCapexSetSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="sheet_name", property="sheetName", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="set_value", property="setValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCapexSet> selectByExampleWithRowbounds(SwCapexSetExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCapexSetSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="sheet_name", property="sheetName", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="set_value", property="setValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCapexSet> selectByExample(SwCapexSetExample example);

    @Select({
        "select",
        "id, task_id, sheet_name, type, number, set_value, is_delete, create_time, datetime_lastchange",
        "from sw_capex_set",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="sheet_name", property="sheetName", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="number", property="number", jdbcType=JdbcType.INTEGER),
        @Result(column="set_value", property="setValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCapexSet selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCapexSetSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCapexSet record, @Param("example") SwCapexSetExample example);

    @UpdateProvider(type=SwCapexSetSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCapexSet record, @Param("example") SwCapexSetExample example);

    @UpdateProvider(type=SwCapexSetSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCapexSet record);

    @Update({
        "update sw_capex_set",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "sheet_name = #{sheetName,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "number = #{number,jdbcType=INTEGER},",
          "set_value = #{setValue,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCapexSet record);
}