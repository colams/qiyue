package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwFeedback;
import com.foxconn.sw.data.entity.SwFeedbackExample;
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
public interface SwFeedbackMapper {
    long countByExample(SwFeedbackExample example);

    int deleteByExample(SwFeedbackExample example);

    @Delete({
        "delete from sw_feedback",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_feedback (employee_no, title, ",
        "remark, contact, ",
        "ip, status, finish_time, ",
        "close_time, create_time, ",
        "datetime_lastchange, content)",
        "values (#{employeeNo,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{contact,jdbcType=VARCHAR}, ",
        "#{ip,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, #{finishTime,jdbcType=TIMESTAMP}, ",
        "#{closeTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwFeedback record);

    int insertSelective(SwFeedback record);

    List<SwFeedback> selectByExampleWithBLOBsWithRowbounds(SwFeedbackExample example, RowBounds rowBounds);

    List<SwFeedback> selectByExampleWithBLOBs(SwFeedbackExample example);

    List<SwFeedback> selectByExampleWithRowbounds(SwFeedbackExample example, RowBounds rowBounds);

    List<SwFeedback> selectByExample(SwFeedbackExample example);

    @Select({
        "select",
        "id, employee_no, title, remark, contact, ip, status, finish_time, close_time, ",
        "create_time, datetime_lastchange, content",
        "from sw_feedback",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwFeedbackMapper.ResultMapWithBLOBs")
    SwFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwFeedback record, @Param("example") SwFeedbackExample example);

    int updateByExampleWithBLOBs(@Param("record") SwFeedback record, @Param("example") SwFeedbackExample example);

    int updateByExample(@Param("record") SwFeedback record, @Param("example") SwFeedbackExample example);

    int updateByPrimaryKeySelective(SwFeedback record);

    @Update({
        "update sw_feedback",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "finish_time = #{finishTime,jdbcType=TIMESTAMP},",
          "close_time = #{closeTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(SwFeedback record);

    @Update({
        "update sw_feedback",
        "set employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "contact = #{contact,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "finish_time = #{finishTime,jdbcType=TIMESTAMP},",
          "close_time = #{closeTime,jdbcType=TIMESTAMP},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwFeedback record);
}