package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwReadStatus;
import com.foxconn.sw.data.entity.SwReadStatusExample;
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
public interface SwReadStatusMapper {
    int deleteByExample(SwReadStatusExample example);

    @Delete({
        "delete from sw_read_status",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_read_status (module_type, foreign_id, ",
        "employee_no, is_read, ",
        "create_time, datetime_lastchange)",
        "values (#{moduleType,jdbcType=VARCHAR}, #{foreignId,jdbcType=INTEGER}, ",
        "#{employeeNo,jdbcType=VARCHAR}, #{isRead,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwReadStatus record);

    int insertSelective(SwReadStatus record);

    List<SwReadStatus> selectByExampleWithRowbounds(SwReadStatusExample example, RowBounds rowBounds);

    List<SwReadStatus> selectByExample(SwReadStatusExample example);

    @Select({
        "select",
        "id, module_type, foreign_id, employee_no, is_read, create_time, datetime_lastchange",
        "from sw_read_status",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwReadStatusMapper.BaseResultMap")
    SwReadStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwReadStatus record, @Param("example") SwReadStatusExample example);

    int updateByExample(@Param("record") SwReadStatus record, @Param("example") SwReadStatusExample example);

    int updateByPrimaryKeySelective(SwReadStatus record);

    @Update({
        "update sw_read_status",
        "set module_type = #{moduleType,jdbcType=VARCHAR},",
          "foreign_id = #{foreignId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "is_read = #{isRead,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwReadStatus record);
}