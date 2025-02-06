package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinuteMember;
import com.foxconn.sw.data.entity.SwMeetingMinuteMemberExample;
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
public interface SwMeetingMinuteMemberMapper {
    @Insert({
        "insert into sw_meeting_minute_member (mm_id, employee_no, ",
        "role, name, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{mmId,jdbcType=BIGINT}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{role,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeetingMinuteMember record);

    @InsertProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMeetingMinuteMember record);

    @SelectProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mm_id", property="mmId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinuteMember> selectByExampleWithRowbounds(SwMeetingMinuteMemberExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mm_id", property="mmId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMinuteMember> selectByExample(SwMeetingMinuteMemberExample example);

    @Select({
        "select",
        "id, mm_id, employee_no, role, name, is_delete, create_time, datetime_lastchange",
        "from sw_meeting_minute_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="mm_id", property="mmId", jdbcType=JdbcType.BIGINT),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingMinuteMember selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingMinuteMember record, @Param("example") SwMeetingMinuteMemberExample example);

    @UpdateProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingMinuteMember record, @Param("example") SwMeetingMinuteMemberExample example);

    @UpdateProvider(type=SwMeetingMinuteMemberSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeetingMinuteMember record);

    @Update({
        "update sw_meeting_minute_member",
        "set mm_id = #{mmId,jdbcType=BIGINT},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeetingMinuteMember record);
}