package com.foxconn.sw.data.mapper.extension.forums;

import com.foxconn.sw.data.entity.extension.ForumBbsExtension;
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
            "select b.*,subquery.sum from forum_bbs b inner join forum_favorite f on b.id=f.fb_id ",
            "left join (",
            "    SELECT p.fb_id," +
                    "           count(1) as sum" +
                    "    FROM forum_favorite p" +
                    "             inner join forum_bbs_comment c",
            "                        on p.fb_id = c.fb_id",
            "             left join sw_read_status r on r.foreign_id = c.id ",
            "        and r.module_type = 'Forum' and r.employee_no=#{currentUser,jdbcType=VARCHAR} ",
            "    WHERE p.operator = #{operator,jdbcType=VARCHAR} ",
            "      and c.is_delete = 0 ",
            "      and p.is_valid = 1 ",
            "      and c.parent_id = 0 ",
            "      and c.target_id = 0 ",
            "      and r.id is null ",
            "    GROUP BY p.fb_id ",
            ") subquery ON b.id = subquery.fb_id",
            "where f.is_valid=1 ",
            "and f.operator = #{operator,jdbcType=VARCHAR} ",
            "and b.is_delete=0 ",
            "<if test='words!=null and words!=\"\"' >",
            " and b.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='isAdmin!=1'>",
            "and f.operator=#{operator,jdbcType=VARCHAR}",
            "</if> ",
            "order by subquery.sum desc, b.id desc",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sum", property = "sum", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
    })
    List<ForumBbsExtension> favoriteBbs(@Param("isAdmin") Integer isAdmin,
                                        @Param("words") String words,
                                        @Param("operator") String operator,
                                        @Param("start") int start,
                                        @Param("pageSize") int pageSize);

    @Select({"<script> " +
            "select b.*,subquery.sum from forum_bbs b ",
            "inner join forum_participant p on b.id=p.fb_id and p.is_delete=0 ",
            "left join (",
            "    SELECT p.fb_id," +
                    "           count(1) as sum" +
                    "    FROM forum_participant p" +
                    "             inner join forum_bbs_comment c",
            "                        on p.fb_id = c.fb_id",
            "             left join sw_read_status r on r.foreign_id = c.id ",
            "        and r.module_type = 'Forum' and r.employee_no=#{currentUser,jdbcType=VARCHAR} ",
            "    WHERE p.employee_no = #{currentUser,jdbcType=VARCHAR} ",
            "      and c.is_delete = 0 ",
            "      and c.parent_id = 0 ",
            "      and c.target_id = 0 ",
            "      and r.id is null ",
            "    GROUP BY p.fb_id ",
            ") subquery ON b.id = subquery.fb_id",
            "where b.is_delete=0 ",
            "<if test='title!=null and title!=\"\"' >",
            " and b.title like #{title,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='owner!=null and owner!=\"\"' >",
            " and b.author_no=#{owner,jdbcType=VARCHAR} ",
            "</if> ",
            "and p.employee_no=#{currentUser,jdbcType=VARCHAR}",
            "and p.hidden=#{hiddenStatus,jdbcType=INTEGER}",
            "and b.status=#{status,jdbcType=INTEGER}",
            "order by subquery.sum desc, b.id desc",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "author_no", property = "authorNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sum", property = "sum", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "lastchange_datetime", property = "lastchangeDatetime", jdbcType = JdbcType.TIMESTAMP),
    })
    List<ForumBbsExtension> selectByKeyWords(@Param("isAdmin") Integer isAdmin,
                                             @Param("currentUser") String currentUser,
                                             @Param("owner") String owner,
                                             @Param("title") String title,
                                             @Param("hiddenStatus") Integer hiddenStatus,
                                             @Param("status") String status,
                                             @Param("start") int start,
                                             @Param("pageSize") int pageSize);

    @Select({"<script> " +
            "select count(1) from forum_bbs b inner join forum_favorite f on " +
            "b.id=f.fb_id " +
            "where f.is_valid=1 and b.is_delete=0 and f.operator=#{operator,jdbcType=VARCHAR}" +
            "<if test='words!=null and words!=\"\"' >",
            " and b.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            " </script> "
    })
    Long getFavoriteBbsCount(@Param("words") String words, @Param("operator") String operator);


    @Select({"<script> " +
            "select count(1) from forum_bbs  b",
            "inner join forum_participant p on b.id=p.fb_id and p.is_delete=0 ",
            "where b.is_delete=0 ",
            "<if test='words!=null and words!=\"\"' >",
            " and b.title like CONCAT('%', #{words,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='employeeNo!=null and employeeNo!=\"\"' >",
            " and b.author_no=#{employeeNo,jdbcType=VARCHAR}",
            "</if> ",
            "and p.hidden=#{hiddenStatus,jdbcType=INTEGER}",
            "and b.status=#{status,jdbcType=INTEGER}",
            " </script> "
    })
    Long selectBbsCount(@Param("words") String words,
                        @Param("employeeNo") String employeeNo,
                        @Param("hiddenStatus") Integer hiddenStatus,
                        @Param("status") String status);

}
