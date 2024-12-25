package com.foxconn.sw.data.mapper.extension.group;

import com.foxconn.sw.data.entity.SwCustomGroupOperate;
import com.foxconn.sw.data.mapper.auto.SwCustomGroupOperateMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwCustomGroupOperateExtMapper extends SwCustomGroupOperateMapper {

    @Select({
            "select o.* ",
            "from sw_custom_group_operate o ",
            "         inner join sw_custom_group_member m ",
            "                    on o.custom_group_id = m.id ",
            "where m.member = #{employeeNo,jdbcType=VARCHAR} ",
            "  and ((m.member_type in (1, 2) and o.operate_type = 'apply' and o.status = 0) or ",
            "       (m.member_type = 0 and o.operate_type = 'apply' and o.status in (1, 2)) ",
            "    or o.operate_type = 'disband')"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "custom_group_id", property = "customGroupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "operate_type", property = "operateType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_read", property = "isRead", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupOperate> getOperateList(String employeeNo);
}
