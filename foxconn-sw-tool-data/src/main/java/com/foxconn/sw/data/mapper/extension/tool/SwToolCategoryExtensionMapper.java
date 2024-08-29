package com.foxconn.sw.data.mapper.extension.tool;

import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.mapper.auto.SwPropertyMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwToolCategoryExtensionMapper extends SwPropertyMapper {

    @Select({"<script> " +
            "select * from sw_property" +
            "where 1=1 " +
            "<if test='category!=null and category!=\"\"' >" +
            "and category=#{category,jdbcType=INTEGER} " +
            "</if> " +
            "order by order_by " +
            " </script> "
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "category", property = "category", jdbcType = JdbcType.INTEGER),
            @Result(column = "property_name", property = "propertyName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "icon", property = "icon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "order_by", property = "orderBy", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwProperty> list(Integer category);

}
