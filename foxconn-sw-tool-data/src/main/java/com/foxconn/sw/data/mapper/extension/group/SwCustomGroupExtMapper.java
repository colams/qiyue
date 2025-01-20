package com.foxconn.sw.data.mapper.extension.group;

import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.mapper.auto.SwCustomGroupMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwCustomGroupExtMapper extends SwCustomGroupMapper {

    @Select({"<script>",
            "select g.* ",
            "from sw_custom_group g inner join sw_custom_group_favorite f on g.id=f.custom_group_id ",
            "<where>",
            "g.is_delete=0 and f.is_delete=0 ",
            "<if test='keywords!=null and keywords!=\"\"' >",
            "and g.name like CONCAT('%', #{keywords,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "owner", property = "owner", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_type", property = "groupType", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_private", property = "isPrivate", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwCustomGroup> listCollectGroup(String keywords);


    @Select({"<script>",
            "select g.* ",
            "from sw_custom_group g inner join sw_custom_group_member m on g.id = m.custom_group_id ",
            "<where>",
            "g.is_delete=0 and m.is_delete=0 ",
            "and m.member= #{employeeNo,jdbcType=VARCHAR} and m.member_type in (1, 2) ",
            "<if test='keywords!=null and keywords!=\"\"' >",
            "and g.name like CONCAT('%', #{keywords,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "</where>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "owner", property = "owner", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_type", property = "groupType", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_private", property = "isPrivate", jdbcType = JdbcType.INTEGER),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwCustomGroup> selectManagerGroups(@Param("employeeNo") String employeeNo, @Param("keywords") String keywords);
}
