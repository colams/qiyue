package com.foxconn.sw.data.mapper.extension.system;

import com.foxconn.sw.data.dto.entity.system.ModuleVo;
import com.foxconn.sw.data.dto.entity.system.PropertiesParams;
import com.foxconn.sw.data.dto.entity.system.PropertyVo;
import com.foxconn.sw.data.mapper.auto.SwPropertyMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwPropertyExtensionMapper extends SwPropertyMapper {


    @Select({"<script>",
            "select * ",
            "from sw_property ",
            "where 1=1 ",
            "<if test='params.propertyName!=null and params.propertyName!=\"\"' >",
            "and property_name like CONCAT('%', #{params.propertyName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.category!=null and params.category>0' >",
            "and category=#{params.category,jdbcType=INTEGER} ",
            "</if> ",
            "ORDER BY id ",
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} ",
            "</script>",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "category", property = "category", jdbcType = JdbcType.INTEGER),
            @Result(column = "property_name", property = "propertyName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "icon", property = "icon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "order_by", property = "orderBy", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<PropertyVo> getPropertyList(@Param("start") int start, @Param("end") int end, @Param("params") PropertiesParams params);

    @Select("SELECT " +
            "    sm.property_name,\n" +
            "    sp.menu_url,\n" +
            "    sp.id\n" +
            "FROM sw_menu sm\n" +
            "        INNER JOIN sw_property sp ON\n" +
            "            sm.module_no = sp.id\n" +
            "WHERE sp.category = #{category,jdbcType=INTEGER}\n" +
            "  AND sm.status = 1\n" +
            "  AND sm.parent_id = 0"
    )
    @Results({
            @Result(column = "id", property = "moduleNo", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "property_name", property = "moduleName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "menu_url", property = "moduleUrl", jdbcType = JdbcType.VARCHAR),
    })
    List<ModuleVo> getModules(@Param("category") Integer category);

    @Select({"<script>",
            "select count(1) ",
            "from sw_property ",
            "where 1=1 ",
            "<if test='propertyName!=null and propertyName!=\"\"' >",
            "and property_name like CONCAT('%', #{propertyName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='category!=null and category>0'   >",
            "and category=#{category,jdbcType=INTEGER} ",
            "</if> ",
            "ORDER BY id ",
            "</script>",
    })
    Integer getTotalCountByParams(PropertiesParams params);
}
