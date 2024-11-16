package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.entity.ForumParticipantExample;
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
public interface ForumParticipantMapper {
    @SelectProvider(type=ForumParticipantSqlProvider.class, method="countByExample")
    long countByExample(ForumParticipantExample example);

    @DeleteProvider(type=ForumParticipantSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumParticipantExample example);

    @Delete({
        "delete from forum_participant",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_participant (posts_id, employee_no, ",
        "create_time, datetime_lastchange)",
        "values (#{postsId,jdbcType=INTEGER}, #{employeeNo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumParticipant record);

    @InsertProvider(type=ForumParticipantSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumParticipant record);

    @SelectProvider(type=ForumParticipantSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumParticipant> selectByExampleWithRowbounds(ForumParticipantExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumParticipantSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumParticipant> selectByExample(ForumParticipantExample example);

    @Select({
        "select",
        "id, posts_id, employee_no, create_time, datetime_lastchange",
        "from forum_participant",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="employee_no", property="employeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumParticipant selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumParticipantSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumParticipant record, @Param("example") ForumParticipantExample example);

    @UpdateProvider(type=ForumParticipantSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumParticipant record, @Param("example") ForumParticipantExample example);

    @UpdateProvider(type=ForumParticipantSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumParticipant record);

    @Update({
        "update forum_participant",
        "set posts_id = #{postsId,jdbcType=INTEGER},",
          "employee_no = #{employeeNo,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumParticipant record);
}