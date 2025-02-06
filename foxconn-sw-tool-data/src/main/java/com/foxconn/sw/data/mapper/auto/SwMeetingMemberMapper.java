package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.entity.SwMeetingMemberExample;
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
public interface SwMeetingMemberMapper {
    int deleteByExample(SwMeetingMemberExample example);

    @Delete({
        "delete from sw_meeting_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_meeting_member (meeting_id, meeting_detail_id, ",
        "employee_no, name, ",
        "is_delete, create_time, ",
        "datetime_lastchange, role)",
        "values (#{meetingId,jdbcType=INTEGER}, #{meetingDetailId,jdbcType=INTEGER}, ",
        "#{employeeNo,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{role,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMeetingMember record);

    int insertSelective(SwMeetingMember record);

    List<SwMeetingMember> selectByExampleWithRowbounds(SwMeetingMemberExample example, RowBounds rowBounds);

    List<SwMeetingMember> selectByExample(SwMeetingMemberExample example);

    @Select({
        "select",
        "id, meeting_id, meeting_detail_id, employee_no, name, is_delete, create_time, ",
        "datetime_lastchange, role",
        "from sw_meeting_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMeetingMemberMapper.BaseResultMap")
    SwMeetingMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwMeetingMember record, @Param("example") SwMeetingMemberExample example);

    int updateByExample(@Param("record") SwMeetingMember record, @Param("example") SwMeetingMemberExample example);

    int updateByPrimaryKeySelective(SwMeetingMember record);

    @Update({
        "update sw_meeting_member",
        "set meeting_id = #{meetingId,jdbcType=INTEGER},",
          "meeting_detail_id = #{meetingDetailId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "role = #{role,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMeetingMember record);
}