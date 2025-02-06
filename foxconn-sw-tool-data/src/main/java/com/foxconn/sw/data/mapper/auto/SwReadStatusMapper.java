package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwReadStatus;
import com.foxconn.sw.data.entity.SwReadStatusExample;
import java.util.List;
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
public interface SwReadStatusMapper {
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

    @InsertProvider(type=SwReadStatusSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwReadStatus record);

    @SelectProvider(type=SwReadStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="foreign_id", property="foreignId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwReadStatus> selectByExampleWithRowbounds(SwReadStatusExample example, RowBounds rowBounds);

    @SelectProvider(type=SwReadStatusSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="foreign_id", property="foreignId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwReadStatus> selectByExample(SwReadStatusExample example);

    @Select({
        "select",
        "id, module_type, foreign_id, employee_no, is_read, create_time, datetime_lastchange",
        "from sw_read_status",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="module_type", property="moduleType", jdbcType=JdbcType.VARCHAR),
        @Result(column="foreign_id", property="foreignId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwReadStatus selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwReadStatusSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwReadStatus record, @Param("example") SwReadStatusExample example);

    @UpdateProvider(type=SwReadStatusSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwReadStatus record, @Param("example") SwReadStatusExample example);

    @UpdateProvider(type=SwReadStatusSqlProvider.class, method="updateByPrimaryKeySelective")
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