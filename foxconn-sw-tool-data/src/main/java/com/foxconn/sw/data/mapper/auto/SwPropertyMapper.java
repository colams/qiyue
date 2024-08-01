package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProperty;
import com.foxconn.sw.data.entity.SwPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwPropertyMapper {
    @SelectProvider(type=SwPropertySqlProvider.class, method="countByExample")
    long countByExample(SwPropertyExample example);

    @DeleteProvider(type=SwPropertySqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwPropertySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwProperty record);

    @SelectProvider(type=SwPropertySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="property_name", property="propertyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProperty> selectByExampleWithRowbounds(SwPropertyExample example, RowBounds rowBounds);

    @SelectProvider(type=SwPropertySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="property_name", property="propertyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwProperty> selectByExample(SwPropertyExample example);

    @Select({
        "select",
        "id, category, property_name, icon, order_by, create_time, datetime_lastchange",
        "from sw_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="category", property="category", jdbcType=JdbcType.INTEGER),
        @Result(column="property_name", property="propertyName", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwProperty selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwPropertySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwProperty record, @Param("example") SwPropertyExample example);

    @UpdateProvider(type=SwPropertySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwProperty record, @Param("example") SwPropertyExample example);

    @UpdateProvider(type=SwPropertySqlProvider.class, method="updateByPrimaryKeySelective")
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