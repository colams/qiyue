package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumComment;
import com.foxconn.sw.data.entity.ForumCommentExample;
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
public interface ForumCommentMapper {
    @SelectProvider(type=ForumCommentSqlProvider.class, method="countByExample")
    long countByExample(ForumCommentExample example);

    @DeleteProvider(type=ForumCommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumCommentExample example);

    @Delete({
        "delete from forum_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_comment (posts_id, parent_id, ",
        "target_id, author_no, ",
        "resources, content, ",
        "create_time, datetime_lastchange)",
        "values (#{postsId,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{targetId,jdbcType=INTEGER}, #{authorNo,jdbcType=VARCHAR}, ",
        "#{resources,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumComment record);

    @InsertProvider(type=ForumCommentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumComment record);

    @SelectProvider(type=ForumCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="resources", property="resources", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumComment> selectByExampleWithRowbounds(ForumCommentExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="resources", property="resources", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumComment> selectByExample(ForumCommentExample example);

    @Select({
        "select",
        "id, posts_id, parent_id, target_id, author_no, resources, content, create_time, ",
        "datetime_lastchange",
        "from forum_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="resources", property="resources", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumComment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumCommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumComment record, @Param("example") ForumCommentExample example);

    @UpdateProvider(type=ForumCommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumComment record, @Param("example") ForumCommentExample example);

    @UpdateProvider(type=ForumCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumComment record);

    @Update({
        "update forum_comment",
        "set posts_id = #{postsId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "target_id = #{targetId,jdbcType=INTEGER},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "resources = #{resources,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumComment record);
}