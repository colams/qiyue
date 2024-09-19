package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.dto.entity.oa.BriefTaskVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.entity.SwTask;
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
            "st.*",
            "from sw_task st ",
            " inner join sw_task_employee_relation ste on ste.task_id=st.id ",
            "<where>",
            " ste.employee_no in ",
            "<foreach collection='employeeNos' open='(' close=')' separator=',' item='employeeNo'>",
            " #{employeeNo} ",
            "</foreach>",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.taskNo!=null and params.taskNo!=0' >",
            " and task_no=#{params.taskNo,jdbcType=BIGINT}",
            "</if> ",
            "<if test='params.title!=null and params.title!=\"\"' >",
            " and title like CONCAT('%', #{params.title,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.proposer!=null and params.proposer!=\"\"' >",
            " and proposer_eid=#{params.proposer,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.project!=null and params.project!=\"\"' >",
            " and project=#{params.project,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.category!=null and params.category!=\"\"' >",
            " and category=#{params.category,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.create_s!=null and params.create_s!=\"\"' >",
            " and st.create_time &gt;= #{params.create_s,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.create_e!=null and params.create_e!=\"\"' >",
            " and st.create_time &lt;= #{params.create_e,jdbcType=VARCHAR}",
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
            "</where>",
            "ORDER BY st.id desc",
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reflection", property = "reflection", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    List<SwTask> listBriefVos(@Param("start") int start,
                              @Param("end") int end,
                              @Param("params") TaskParams params,
                              @Param("employeeNos") List<String> employeeNos,
                              @Param("nowDate") String nowDate);

    @Select({"<script>",
            "select count(1)",
            "from sw_task st ",
            " inner join sw_task_employee_relation ste on ste.task_id=st.id ",
            " left join sw_employee se1 on st.proposer_eid=se1.employee_no ",
            " left join sw_employee se2 on ste.employee_no=se2.employee_no ",
            "<where>",
            " ste.employee_no in ",
            "<foreach collection='employeeNos' open='(' close=')' separator=',' item='employeeNo'>",
            " #{employeeNo} ",
            "</foreach>",
            "<if test='params.keyWord!=null and params.keyWord!=\"\"' >",
            " and title like CONCAT('%', #{params.keyWord,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.taskNo!=null and params.taskNo!=0' >",
            " and task_no=#{params.taskNo,jdbcType=BIGINT}",
            "</if> ",
            "<if test='params.title!=null and params.title!=\"\"' >",
            " and title like CONCAT('%', #{params.title,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.proposer!=null and params.proposer!=\"\"' >",
            " and proposer_eid=#{params.proposer,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.project!=null and params.project!=\"\"' >",
            " and project=#{params.project,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.category!=null and params.category!=\"\"' >",
            " and category=#{params.category,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.create_s!=null and params.create_s!=\"\"' >",
            " and st.create_time &gt;= #{params.create_s,jdbcType=VARCHAR}",
            "</if> ",
            "<if test='params.create_e!=null and params.create_e!=\"\"' >",
            " and st.create_time &lt;= #{params.create_e,jdbcType=VARCHAR}",
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
            "</where>",
            "ORDER BY st.id ",
            "</script>"
    })
    int getTotalCountByParams(@Param("params") TaskParams params, @Param("employeeNos") List<String> employeeNos, @Param("nowDate") String nowDate);

    @Select({
            "select * ",
            "from sw_task",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "task_no", property = "taskNo", jdbcType = JdbcType.BIGINT),
            @Result(column = "top_category", property = "topCategory", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "top_project", property = "topProject", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "level", property = "level", jdbcType = JdbcType.VARCHAR),
            @Result(column = "progress_percent", property = "progressPercent", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "reject_status", property = "rejectStatus", jdbcType = JdbcType.INTEGER),
            @Result(column = "proposer_eid", property = "proposerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "manager_eid", property = "managerEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "handle_eid", property = "handleEid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reflection", property = "reflection", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR)
    })
    BriefTaskVo selectByTaskId(Integer id);
}
