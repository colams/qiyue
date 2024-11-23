package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.entity.SwConfigDicExample;
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
public interface SwConfigDicMapper {
    @SelectProvider(type=SwConfigDicSqlProvider.class, method="countByExample")
    long countByExample(SwConfigDicExample example);

    @DeleteProvider(type=SwConfigDicSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwConfigDicExample example);

    @Delete({
        "delete from sw_config_dic",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_config_dic (item, item_value, ",
        "is_disable, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{item,jdbcType=VARCHAR}, #{itemValue,jdbcType=VARCHAR}, ",
        "#{isDisable,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwConfigDic record);

    @InsertProvider(type=SwConfigDicSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwConfigDic record);

    @SelectProvider(type=SwConfigDicSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_disable", property="isDisable", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwConfigDic> selectByExampleWithRowbounds(SwConfigDicExample example, RowBounds rowBounds);

    @SelectProvider(type=SwConfigDicSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_disable", property="isDisable", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwConfigDic> selectByExample(SwConfigDicExample example);

    @Select({
        "select",
        "id, item, item_value, is_disable, is_delete, create_time, datetime_lastchange",
        "from sw_config_dic",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_disable", property="isDisable", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwConfigDic selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwConfigDicSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwConfigDic record, @Param("example") SwConfigDicExample example);

    @UpdateProvider(type=SwConfigDicSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwConfigDic record, @Param("example") SwConfigDicExample example);

    @UpdateProvider(type=SwConfigDicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwConfigDic record);

    @Update({
        "update sw_config_dic",
        "set item = #{item,jdbcType=VARCHAR},",
          "item_value = #{itemValue,jdbcType=VARCHAR},",
          "is_disable = #{isDisable,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwConfigDic record);
}