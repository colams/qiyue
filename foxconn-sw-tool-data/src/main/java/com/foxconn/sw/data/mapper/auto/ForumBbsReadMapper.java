package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumBbsRead;
import com.foxconn.sw.data.entity.ForumBbsReadExample;
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
public interface ForumBbsReadMapper {
    @SelectProvider(type=ForumBbsReadSqlProvider.class, method="countByExample")
    long countByExample(ForumBbsReadExample example);

    @DeleteProvider(type=ForumBbsReadSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumBbsReadExample example);

    @Delete({
        "delete from forum_bbs_read",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_bbs_read (comment_id, employee_no, ",
        "is_read, create_time, ",
        "datetime_lastchange)",
        "values (#{commentId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{isRead,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumBbsRead record);

    @InsertProvider(type=ForumBbsReadSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumBbsRead record);

    @SelectProvider(type=ForumBbsReadSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbsRead> selectByExampleWithRowbounds(ForumBbsReadExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumBbsReadSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbsRead> selectByExample(ForumBbsReadExample example);

    @Select({
        "select",
        "id, comment_id, employee_no, is_read, create_time, datetime_lastchange",
        "from forum_bbs_read",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumBbsRead selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumBbsReadSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumBbsRead record, @Param("example") ForumBbsReadExample example);

    @UpdateProvider(type=ForumBbsReadSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumBbsRead record, @Param("example") ForumBbsReadExample example);

    @UpdateProvider(type=ForumBbsReadSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumBbsRead record);

    @Update({
        "update forum_bbs_read",
        "set comment_id = #{commentId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "is_read = #{isRead,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumBbsRead record);
}