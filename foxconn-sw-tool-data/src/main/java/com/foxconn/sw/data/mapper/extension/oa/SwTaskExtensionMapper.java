package com.foxconn.sw.data.mapper.extension.oa;

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
            "select ",
            "st.id,task_no, reject_status,top_category, category, title, top_project, project, description, level, ",
            "progress_percent, st.status, proposer_eid, se1.name as proposer,handle_eid, se3.name as handler,se2.name as handler2,manager_eid, ",
            "dead_line, resource_ids, start_date, end_date, st.create_time,ster2.prev_id as next_id",
            "from sw_task st ",
            " inner join sw_task_employee_relation ster on ster.task_id=st.id ",
            " left join sw_task_employee_relation ster2 on ster2.prev_id=ster.id ",
            " left join sw_employee se1 on st.proposer_eid=se1.employee_no ",
            " left join sw_employee se2 on ster.employee_no=se2.employee_no ",
            " left join sw_employee se3 on ster2.employee_no=se3.employee_no ",
            "where ster.employee_no=#{employeeId,jdbcType=VARCHAR} ",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.searchType==1'  >",
            " and st.status=1 ",
            "</if> ",
            "<if test='params.searchType==2'  >",
            " and st.status=2 ",
            "</if> ",
            "<if test='params.searchType==3'  >",
            " and st.status=3 ",
            "</if> ",
            "<if test='params.searchType==4'  >",
            " and st.status in (1,2,3) and dead_line &lt;#{nowDate,jdbcType=VARCHAR} ",
            "</if> ",
            "ORDER BY st.id desc",
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer", property = "proposer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "proposer_eid", property = "proposerEID", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEID", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handler", property = "handler", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handler2", property = "handler2", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handlerEID", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "start_date", property = "startDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "end_date", property = "endDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "next_id", property = "nextID", jdbcType = JdbcType.INTEGER),
    })
    List<TaskBriefListVo> listBriefVos(@Param("start") int start, @Param("end") int end, @Param("params") TaskParams params, @Param("employeeId") String employeeId, @Param("nowDate") String nowDate);

    @Select({"<script>",
            "select count(1)",
            "from sw_task st ",
            " inner join sw_task_employee_relation ster on ster.task_id=st.id ",
            " left join sw_task_employee_relation ster2 on ster2.prev_id=ster.id ",
            " left join sw_employee se1 on st.proposer_eid=se1.employee_no ",
            " left join sw_employee se2 on ster.employee_no=se2.employee_no ",
            " left join sw_employee se3 on ster2.employee_no=se3.employee_no ",
            "where ster.employee_no=#{employeeId,jdbcType=VARCHAR} ",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.searchType==1'  >",
            " and st.status=1 ",
            "</if> ",
            "<if test='params.searchType==2'  >",
            " and st.status=2 ",
            "</if> ",
            "<if test='params.searchType==3'  >",
            " and st.status=3 ",
            "</if> ",
            "<if test='params.searchType==4'  >",
            " and st.status in (1,2,3) and dead_line &lt;#{nowDate,jdbcType=VARCHAR} ",
            "</if> ",
            "ORDER BY st.id ",
            "</script>"
    })
    int getTotalCountByParams(@Param("params") TaskParams params, @Param("employeeId") String employeeId, @Param("nowDate") String nowDate);
}
