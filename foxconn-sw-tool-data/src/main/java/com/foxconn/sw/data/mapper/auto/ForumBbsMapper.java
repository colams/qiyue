package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsExample;
import java.util.List;
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
public interface ForumBbsMapper {
    @DeleteProvider(type=ForumBbsSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumBbsExample example);

    @Insert({
        "insert into forum_bbs (project, title, ",
        "author_no, status, ",
        "archive, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{project,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{authorNo,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{archive,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumBbs record);

    @InsertProvider(type=ForumBbsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumBbs record);

    @SelectProvider(type=ForumBbsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="archive", property="archive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbs> selectByExampleWithRowbounds(ForumBbsExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumBbsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="archive", property="archive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbs> selectByExample(ForumBbsExample example);

    @Select({
        "select",
        "id, project, title, author_no, status, archive, is_delete, create_time, datetime_lastchange",
        "from forum_bbs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="archive", property="archive", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumBbs selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumBbsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumBbs record, @Param("example") ForumBbsExample example);

    @UpdateProvider(type=ForumBbsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumBbs record, @Param("example") ForumBbsExample example);

    @UpdateProvider(type=ForumBbsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumBbs record);

    @Update({
        "update forum_bbs",
        "set project = #{project,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "archive = #{archive,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumBbs record);
}