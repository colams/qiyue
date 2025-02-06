package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.entity.SwPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwPropertyMapper {
    int deleteByExample(SwPropertyExample example);

    @Delete({
        "delete from sw_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_property (category, property_name, ",
        "icon, order_by, create_time, ",
        "datetime_lastchange)",
        "values (#{category,jdbcType=INTEGER}, #{propertyName,jdbcType=VARCHAR}, ",
        "#{icon,jdbcType=VARCHAR}, #{orderBy,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwProperty record);

    int insertSelective(SwProperty record);

    List<SwProperty> selectByExampleWithRowbounds(SwPropertyExample example, RowBounds rowBounds);

    List<SwProperty> selectByExample(SwPropertyExample example);

    @Select({
        "select",
        "id, category, property_name, icon, order_by, create_time, datetime_lastchange",
        "from sw_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwPropertyMapper.BaseResultMap")
    SwProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwProperty record, @Param("example") SwPropertyExample example);

    int updateByExample(@Param("record") SwProperty record, @Param("example") SwPropertyExample example);

    int updateByPrimaryKeySelective(SwProperty record);

    @Update({
        "update sw_property",
        "set category = #{category,jdbcType=INTEGER},",
          "property_name = #{propertyName,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "order_by = #{orderBy,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwProperty record);
}