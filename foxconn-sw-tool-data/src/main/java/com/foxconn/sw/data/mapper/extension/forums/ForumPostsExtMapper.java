package com.foxconn.sw.data.mapper.extension.forums;

import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.mapper.auto.ForumPostsMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostsExtMapper extends ForumPostsMapper {

    @Select({"<script> " +
            "select p.* from forum_posts p inner join forum_favorite f on " +
            "p.id=f.posts_id " +
            "where f.is_invalid=0" +
            "<if test='words!=null and words!=\"\"' >",
            " and p.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "resource_ids", property = "resourceIds", jdbcType = JdbcType.VARCHAR),
            @Result(column = "purview", property = "purview", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "lastchange_datetime", property = "lastchangeDatetime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<ForumPosts> favoritePosts(String words);


}
