package com.foxconn.sw.data.mapper.extension.forums;

import com.foxconn.sw.data.mapper.auto.ForumBbsCommentMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumBbsCommentExtMapper extends ForumBbsCommentMapper {
    @Select({"<script> ",
            "select count(1) from forum_bbs_comment  ",
            "where is_delete=0 ",
            "and fb_id=#{params,jdbcType=INTEGER}",
            " </script> "
    })
    Long queryCountByBbsId(Integer params);
}
