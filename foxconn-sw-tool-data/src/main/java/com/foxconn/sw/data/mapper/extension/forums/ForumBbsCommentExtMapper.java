package com.foxconn.sw.data.mapper.extension.forums;

import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumBbsCommentExample;
import com.foxconn.sw.data.mapper.auto.ForumBbsCommentMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumBbsCommentExtMapper extends ForumBbsCommentMapper {
    @Select({"<script> ",
            "select count(1) from forum_bbs_comment  ",
            "where is_delete=0 ",
            "and fb_id=#{params,jdbcType=INTEGER}",
            " </script> "
    })
    Long queryCountByBbsId(Integer params);

    @Select({
            "select",
            "id, fb_id, parent_id, target_id, author_no, is_delete, create_time, ",
            "datetime_lastchange, content",
            "from forum_bbs_comment",
            "where fb_id = #{id,jdbcType=INTEGER}",
            "order by id desc",
            "limit 1"
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
    ForumBbsComment selectByFbId(Integer id);

}
