package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.entity.SwMeetingMemberExample;
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
public interface SwMeetingMemberMapper {
    @SelectProvider(type=SwMeetingMemberSqlProvider.class, method="countByExample")
    long countByExample(SwMeetingMemberExample example);

    @DeleteProvider(type=SwMeetingMemberSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwMeetingMemberExample example);

    @Delete({
        "delete from sw_meeting_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_meeting_member (meeting_id, role, ",
        "employee_no, name, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{meetingId,jdbcType=INTEGER}, #{role,jdbcType=INTEGER}, ",
        "#{employeeNo,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeetingMember record);

    @InsertProvider(type=SwMeetingMemberSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMeetingMember record);

    @SelectProvider(type=SwMeetingMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMember> selectByExampleWithRowbounds(SwMeetingMemberExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMeetingMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMeetingMember> selectByExample(SwMeetingMemberExample example);

    @Select({
        "select",
        "id, meeting_id, role, employee_no, name, is_delete, create_time, datetime_lastchange",
        "from sw_meeting_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="meeting_id", property="meetingId", jdbcType=JdbcType.INTEGER),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMeetingMember selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwMeetingMemberSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMeetingMember record, @Param("example") SwMeetingMemberExample example);

    @UpdateProvider(type=SwMeetingMemberSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMeetingMember record, @Param("example") SwMeetingMemberExample example);

    @UpdateProvider(type=SwMeetingMemberSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwMeetingMember record);

    @Update({
        "update sw_meeting_member",
        "set meeting_id = #{meetingId,jdbcType=INTEGER},",
          "role = #{role,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeetingMember record);
}