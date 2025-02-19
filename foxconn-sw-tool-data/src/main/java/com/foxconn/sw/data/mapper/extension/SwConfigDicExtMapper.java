package com.foxconn.sw.data.mapper.extension;

import com.foxconn.sw.data.dto.request.config.ListParams;
import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.mapper.auto.SwConfigDicMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwConfigDicExtMapper extends SwConfigDicMapper {


    @Select({"<script>",
            "select *",
            "from sw_config_dic",
            "<where>",
            " and is_disable=0 ",
            "<if test='params.itemName!=null and params.itemName!=\"\"' >",
            " and item like CONCAT('%', #{params.itemName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.disable!=null and params.disable!=\"\"' >",
            " and is_disable=#{params.disable,jdbcType=INTEGER}",
            "</if> ",
            "</where>",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "item", property = "item", jdbcType = JdbcType.VARCHAR),
            @Result(column = "item_value", property = "itemValue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_disable", property = "isDisable", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwConfigDic> selectListParams(ListParams params, int start, Integer pageSize);

    @Select({"<script>",
            "select",
            "id, item, item_value, is_disable, is_delete, create_time, datetime_lastchange",
            "from sw_config_dic",
            "<where>",
            "<if test='itemName!=null and itemName!=\"\"' >",
            " and item like CONCAT('%', #{itemName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "</where>",
            "LIMIT 1",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "item", property = "item", jdbcType = JdbcType.VARCHAR),
            @Result(column = "item_value", property = "itemValue", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_disable", property = "isDisable", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    SwConfigDic selectSwConfigDic(String itemName);

}
