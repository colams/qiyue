package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMinuteMember;
import com.foxconn.sw.data.entity.SwMeetingMinuteMemberExample;
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
public interface SwMeetingMinuteMemberMapper {
    int deleteByExample(SwMeetingMinuteMemberExample example);

    @Delete({
        "delete from sw_meeting_minute_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

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

    int insertSelective(SwMeetingMinuteMember record);

    List<SwMeetingMinuteMember> selectByExampleWithRowbounds(SwMeetingMinuteMemberExample example, RowBounds rowBounds);

    List<SwMeetingMinuteMember> selectByExample(SwMeetingMinuteMemberExample example);

    @Select({
        "select",
        "id, mm_id, employee_no, role, name, is_delete, create_time, datetime_lastchange",
        "from sw_meeting_minute_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingMinuteMemberMapper.BaseResultMap")
    SwMeetingMinuteMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwMeetingMinuteMember record, @Param("example") SwMeetingMinuteMemberExample example);

    int updateByExample(@Param("record") SwMeetingMinuteMember record, @Param("example") SwMeetingMinuteMemberExample example);

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