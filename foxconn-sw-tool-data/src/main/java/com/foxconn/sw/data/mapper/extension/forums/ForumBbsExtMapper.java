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
            "select b.* from forum_bbs b inner join forum_favorite f on b.id=f.posts_id ",
            "inner join forum_participant p on b.id=p.posts_id and p.is_delete=0",
            "where f.is_valid=1 ",
            "and b.is_delete=0 ",
            "and f.operator=#{operator,jdbcType=VARCHAR}",
            "<if test='words!=null and words!=\"\"' >",
            " and b.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='isAdmin!=1'>",
            "and p.employee_no=#{operator,jdbcType=VARCHAR}",
            "</if> ",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
    })
    List<ForumBbs> favoriteBbs(@Param("isAdmin") Integer isAdmin,
                               @Param("words") String words,
                               @Param("operator") String operator,
                               @Param("start") int start,
                               @Param("pageSize") int pageSize);

    @Select({"<script> " +
            "select count(1) from forum_bbs b inner join forum_favorite f on " +
            "b.id=f.posts_id " +
            "where f.is_valid=1 and b.is_delete=0 and f.operator=#{operator,jdbcType=VARCHAR}" +
            "<if test='words!=null and words!=\"\"' >",
            " and b.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            " </script> "
    })
    Long getFavoriteBbsCount(@Param("words") String words, @Param("operator") String operator);


    @Select({"<script> " +
            "select count(1) from forum_bbs  ",
            "where is_delete=0 ",
            "<if test='words!=null and words!=\"\"' >",
            " and title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='employeeNo!=null and employeeNo!=\"\"' >",
            " and author_no=#{employeeNo,jdbcType=VARCHAR}",
            "</if> ",
            " </script> "
    })
    Long selectBbsCount(@Param("words") String words, @Param("employeeNo") String employeeNo);

    @Select({"<script> " +
            "select b.* from forum_bbs b ",
            "<if test='isAdmin!=1'>",
            "inner join forum_participant p on b.id=p.posts_id and p.is_delete=0 ",
            "</if> ",
            "where b.is_delete=0 ",
            "<if test='title!=null and title!=\"\"' >",
            " and b.title like #{title,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='owner!=null and owner!=\"\"' >",
            " and b.author_no=#{owner,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='isAdmin!=1'>",
            "and p.employee_no=#{currentUser,jdbcType=VARCHAR}",
            "</if> ",
            "order by b.create_time desc",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "lastchange_datetime", property = "lastchangeDatetime", jdbcType = JdbcType.TIMESTAMP),
    })
    List<ForumBbs> selectByKeyWords(@Param("isAdmin") Integer isAdmin,
                                    @Param("currentUser") String currentUser,
                                    @Param("owner") String owner,
                                    @Param("title") String title,
                                    @Param("start") int start,
                                    @Param("pageSize") int pageSize);
}
