package com.foxconn.sw.data.mapper.extension.forums;

import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.mapper.auto.ForumBbsMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumBbsExtMapper extends ForumBbsMapper {

    @Select({"<script> " +
            "select b.* from forum_bbs b inner join forum_favorite f on " +
            "b.id=f.posts_id " +
            "where f.is_valid=1 and p.is_delete=0 and f.operator=#{operator,jdbcType=VARCHAR}" +
            "<if test='words!=null and words!=\"\"' >",
            " and p.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "lastchange_datetime", property = "lastchangeDatetime", jdbcType = JdbcType.TIMESTAMP),
    })
    List<ForumBbs> favoriteBbs(@Param("words") String words, @Param("operator") String operator);


}
