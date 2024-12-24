package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumAttachment;
import com.foxconn.sw.data.entity.ForumAttachmentExample;
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
public interface ForumAttachmentMapper {
    @SelectProvider(type=ForumAttachmentSqlProvider.class, method="countByExample")
    long countByExample(ForumAttachmentExample example);

    @DeleteProvider(type=ForumAttachmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumAttachmentExample example);

    @Delete({
        "delete from forum_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_attachment (posts_id, comment_id, ",
        "resource_id, is_delete, ",
        "create_time, datatime_lastchange)",
        "values (#{postsId,jdbcType=INTEGER}, #{commentId,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datatimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumAttachment record);

    @InsertProvider(type=ForumAttachmentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumAttachment record);

    @SelectProvider(type=ForumAttachmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumAttachment> selectByExampleWithRowbounds(ForumAttachmentExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumAttachmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumAttachment> selectByExample(ForumAttachmentExample example);

    @Select({
        "select",
        "id, posts_id, comment_id, resource_id, is_delete, create_time, datatime_lastchange",
        "from forum_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment_id", property="commentId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumAttachment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumAttachmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumAttachment record, @Param("example") ForumAttachmentExample example);

    @UpdateProvider(type=ForumAttachmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumAttachment record, @Param("example") ForumAttachmentExample example);

    @UpdateProvider(type=ForumAttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumAttachment record);

    @Update({
        "update forum_attachment",
        "set posts_id = #{postsId,jdbcType=INTEGER},",
          "comment_id = #{commentId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datatime_lastchange = #{datatimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumAttachment record);
}