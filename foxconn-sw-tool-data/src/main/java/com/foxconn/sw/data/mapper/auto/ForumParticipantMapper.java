package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.entity.ForumParticipantExample;
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
public interface ForumParticipantMapper {
    long countByExample(ForumParticipantExample example);

    int deleteByExample(ForumParticipantExample example);

    @Delete({
        "delete from forum_participant",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_participant (fb_id, employee_no, ",
        "is_read, hidden, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{fbId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{isRead,jdbcType=INTEGER}, #{hidden,jdbcType=INTEGER}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumParticipant record);

    int insertSelective(ForumParticipant record);

    List<ForumParticipant> selectByExampleWithRowbounds(ForumParticipantExample example, RowBounds rowBounds);

    List<ForumParticipant> selectByExample(ForumParticipantExample example);

    @Select({
        "select",
        "id, fb_id, employee_no, is_read, hidden, is_delete, create_time, datetime_lastchange",
        "from forum_participant",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.ForumParticipantMapper.BaseResultMap")
    ForumParticipant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumParticipant record, @Param("example") ForumParticipantExample example);

    int updateByExample(@Param("record") ForumParticipant record, @Param("example") ForumParticipantExample example);

    int updateByPrimaryKeySelective(ForumParticipant record);

    @Update({
        "update forum_participant",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "is_read = #{isRead,jdbcType=INTEGER},",
          "hidden = #{hidden,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumParticipant record);
}