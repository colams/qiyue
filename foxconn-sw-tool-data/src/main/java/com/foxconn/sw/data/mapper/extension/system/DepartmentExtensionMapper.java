package com.foxconn.sw.data.mapper.extension.system;

import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.mapper.auto.SwDepartmentMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentExtensionMapper extends SwDepartmentMapper {

    @Select({
            "select",
            "id, name, parent_id,manager_no",
            "from sw_department",
            "where status=1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
            @Result(column="manager_no", property="managerNo", jdbcType=JdbcType.VARCHAR),
    })
    List<DepartmentVo> getDepartList();
}
