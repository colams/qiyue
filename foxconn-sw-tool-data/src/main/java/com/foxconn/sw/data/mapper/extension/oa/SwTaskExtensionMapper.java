package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.mapper.auto.SwTaskMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwTaskExtensionMapper extends SwTaskMapper {


    @Select({"<script>",
            "select *",
            "from sw_task",
            "where 1=1",
            "<if test='employeeId!=null and employeeId!=\"\"' >",
            " and (proposer_eid=#{employeeId,jdbcType=VARCHAR} or manager_eid=#{employeeId,jdbcType=VARCHAR} or handle_eid=#{employeeId,jdbcType=VARCHAR})",
            "</if> ",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.status!=null' >",
            " and status=#{params.status,jdbcType=INTEGER} ",
            "</if> ",
            "ORDER BY id ",
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
    })
    List<TaskBriefListVo> listBriefVos(@Param("start") int start, @Param("end") int end, @Param("params") TaskParams params, @Param("employeeId") String employeeId);

    @Select({"<script>",
            "select count(1)",
            "from sw_task",
            "where 1=1",
            "<if test='employeeId!=null and employeeId!=\"\"' >",
            " and (proposer_eid=#{employeeId,jdbcType=VARCHAR} or manager_eid=#{employeeId,jdbcType=VARCHAR} or handle_eid=#{employeeId,jdbcType=VARCHAR})",
            "</if> ",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.status!=null' >",
            " and status=#{params.status,jdbcType=INTEGER} ",
            "</if> ",
            "ORDER BY id ",
            "</script>"
    })
    int getTotalCountByParams(@Param("params") TaskParams params, @Param("employeeId") String employeeId);
}
