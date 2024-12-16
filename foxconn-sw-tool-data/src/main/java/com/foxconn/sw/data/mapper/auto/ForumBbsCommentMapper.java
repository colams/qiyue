package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumBbsCommentExample;
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
public interface ForumBbsCommentMapper {
    @SelectProvider(type=ForumBbsCommentSqlProvider.class, method="countByExample")
    long countByExample(ForumBbsCommentExample example);

    @DeleteProvider(type=ForumBbsCommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumBbsCommentExample example);

    @Delete({
        "delete from forum_bbs_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_bbs_comment (fb_id, parent_id, ",
        "target_id, author_no, ",
        "is_delete, create_time, ",
        "datetime_lastchange, content)",
        "values (#{fbId,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{targetId,jdbcType=INTEGER}, #{authorNo,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumBbsComment record);

    @InsertProvider(type=ForumBbsCommentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumBbsComment record);

    @SelectProvider(type=ForumBbsCommentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ForumBbsComment> selectByExampleWithBLOBsWithRowbounds(ForumBbsCommentExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumBbsCommentSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ForumBbsComment> selectByExampleWithBLOBs(ForumBbsCommentExample example);

    @SelectProvider(type=ForumBbsCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbsComment> selectByExampleWithRowbounds(ForumBbsCommentExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumBbsCommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumBbsComment> selectByExample(ForumBbsCommentExample example);

    @Select({
        "select",
        "id, fb_id, parent_id, target_id, author_no, is_delete, create_time, datetime_lastchange, ",
        "content",
        "from forum_bbs_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="target_id", property="targetId", jdbcType=JdbcType.INTEGER),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    ForumBbsComment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumBbsCommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    @UpdateProvider(type=ForumBbsCommentSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    @UpdateProvider(type=ForumBbsCommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    @UpdateProvider(type=ForumBbsCommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumBbsComment record);

    @Update({
        "update forum_bbs_comment",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "target_id = #{targetId,jdbcType=INTEGER},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ForumBbsComment record);

    @Update({
        "update forum_bbs_comment",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "target_id = #{targetId,jdbcType=INTEGER},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumBbsComment record);
}