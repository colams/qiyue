package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumPostsAttachment;
import com.foxconn.sw.data.entity.ForumPostsAttachmentExample;
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
public interface ForumPostsAttachmentMapper {
    @SelectProvider(type=ForumPostsAttachmentSqlProvider.class, method="countByExample")
    long countByExample(ForumPostsAttachmentExample example);

    @DeleteProvider(type=ForumPostsAttachmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumPostsAttachmentExample example);

    @Delete({
        "delete from forum_posts_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_posts_attachment (posts_id, resource_id, ",
        "is_delete, create_time, ",
        "datatime_lastchange)",
        "values (#{postsId,jdbcType=INTEGER}, #{resourceId,jdbcType=INTEGER}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datatimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumPostsAttachment record);

    @InsertProvider(type=ForumPostsAttachmentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumPostsAttachment record);

    @SelectProvider(type=ForumPostsAttachmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumPostsAttachment> selectByExampleWithRowbounds(ForumPostsAttachmentExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumPostsAttachmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumPostsAttachment> selectByExample(ForumPostsAttachmentExample example);

    @Select({
        "select",
        "id, posts_id, resource_id, is_delete, create_time, datatime_lastchange",
        "from forum_posts_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="posts_id", property="postsId", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumPostsAttachment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumPostsAttachmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumPostsAttachment record, @Param("example") ForumPostsAttachmentExample example);

    @UpdateProvider(type=ForumPostsAttachmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumPostsAttachment record, @Param("example") ForumPostsAttachmentExample example);

    @UpdateProvider(type=ForumPostsAttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumPostsAttachment record);

    @Update({
        "update forum_posts_attachment",
        "set posts_id = #{postsId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datatime_lastchange = #{datatimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumPostsAttachment record);
}