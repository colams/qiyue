package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsExample;
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
public interface ForumPostsMapper {
    @SelectProvider(type=ForumPostsSqlProvider.class, method="countByExample")
    long countByExample(ForumPostsExample example);

    @DeleteProvider(type=ForumPostsSqlProvider.class, method="deleteByExample")
    int deleteByExample(ForumPostsExample example);

    @Delete({
        "delete from forum_posts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_posts (title, author_no, ",
        "purview, is_delete, ",
        "create_time, lastchange_datetime, ",
        "description)",
        "values (#{title,jdbcType=VARCHAR}, #{authorNo,jdbcType=VARCHAR}, ",
        "#{purview,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastchangeDatetime,jdbcType=TIMESTAMP}, ",
        "#{description,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumPosts record);

    @InsertProvider(type=ForumPostsSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumPosts record);

    @SelectProvider(type=ForumPostsSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="purview", property="purview", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastchange_datetime", property="lastchangeDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ForumPosts> selectByExampleWithBLOBsWithRowbounds(ForumPostsExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumPostsSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="purview", property="purview", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastchange_datetime", property="lastchangeDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<ForumPosts> selectByExampleWithBLOBs(ForumPostsExample example);

    @SelectProvider(type=ForumPostsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="purview", property="purview", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastchange_datetime", property="lastchangeDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumPosts> selectByExampleWithRowbounds(ForumPostsExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumPostsSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="purview", property="purview", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastchange_datetime", property="lastchangeDatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumPosts> selectByExample(ForumPostsExample example);

    @Select({
        "select",
        "id, title, author_no, purview, is_delete, create_time, lastchange_datetime, ",
        "description",
        "from forum_posts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author_no", property="authorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="purview", property="purview", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastchange_datetime", property="lastchangeDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR)
    })
    ForumPosts selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumPostsSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumPosts record, @Param("example") ForumPostsExample example);

    @UpdateProvider(type=ForumPostsSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ForumPosts record, @Param("example") ForumPostsExample example);

    @UpdateProvider(type=ForumPostsSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumPosts record, @Param("example") ForumPostsExample example);

    @UpdateProvider(type=ForumPostsSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumPosts record);

    @Update({
        "update forum_posts",
        "set title = #{title,jdbcType=VARCHAR},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "purview = #{purview,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "lastchange_datetime = #{lastchangeDatetime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ForumPosts record);

    @Update({
        "update forum_posts",
        "set title = #{title,jdbcType=VARCHAR},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "purview = #{purview,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "lastchange_datetime = #{lastchangeDatetime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumPosts record);
}