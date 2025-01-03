package com.foxconn.sw.data.mapper.extension.collaboration;

import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.mapper.auto.SwCollaborationDetailMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwCollaborationDetailExtensionMapper extends SwCollaborationDetailMapper {

    @Select({
            "select",
            "sd.* ",
            "from sw_collaboration_detail sd inner join sw_collaboration_user sc on sd.scu_id=sc.id",
            "where task_id = #{taskID,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="scu_id", property="scuId", jdbcType=JdbcType.BIGINT),
            @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
            @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
            @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
            @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
            @Result(column="spare_value", property="spareValue", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationDetail> selectCollaborationsByTaskID(Long taskID);
}
