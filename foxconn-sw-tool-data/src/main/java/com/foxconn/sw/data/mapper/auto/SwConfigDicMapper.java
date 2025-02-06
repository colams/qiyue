package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwConfigDic;
import com.foxconn.sw.data.entity.SwConfigDicExample;
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
public interface SwConfigDicMapper {
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

    int insertSelective(SwConfigDic record);

    List<SwConfigDic> selectByExampleWithRowbounds(SwConfigDicExample example, RowBounds rowBounds);

    List<SwConfigDic> selectByExample(SwConfigDicExample example);

    @Select({
        "select",
        "id, item, item_value, is_disable, is_delete, create_time, datetime_lastchange",
        "from sw_config_dic",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwConfigDicMapper.BaseResultMap")
    SwConfigDic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwConfigDic record, @Param("example") SwConfigDicExample example);

    int updateByExample(@Param("record") SwConfigDic record, @Param("example") SwConfigDicExample example);

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