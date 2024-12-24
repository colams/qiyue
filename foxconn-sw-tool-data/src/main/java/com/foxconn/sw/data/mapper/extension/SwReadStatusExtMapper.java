package com.foxconn.sw.data.mapper.extension;

import com.foxconn.sw.data.mapper.auto.SwReadStatusMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SwReadStatusExtMapper extends SwReadStatusMapper {

    @Select({"<script> " +
            "select count(1) " +
            "from forum_bbs_comment b " +
            "         left join sw_read_status s on b.id = s.foreign_id and s.module_type = #{moduleType,jdbcType=VARCHAR} ",
            "where b.fb_id=#{bbsId,jdbcType=INTEGER} ",
            "and s.id is null",
            " </script> "
    })
    int getForumUnReadCount(@Param("moduleType") String moduleType, @Param("bbsId") Integer bbsId);
}
