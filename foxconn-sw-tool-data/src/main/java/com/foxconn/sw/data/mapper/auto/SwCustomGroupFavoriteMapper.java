package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import com.foxconn.sw.data.entity.SwCustomGroupFavoriteExample;
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
public interface SwCustomGroupFavoriteMapper {
    int deleteByExample(SwCustomGroupFavoriteExample example);

    @Delete({
        "delete from sw_custom_group_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_custom_group_favorite (custom_group_id, operator, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{customGroupId,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroupFavorite record);

    int insertSelective(SwCustomGroupFavorite record);

    List<SwCustomGroupFavorite> selectByExampleWithRowbounds(SwCustomGroupFavoriteExample example, RowBounds rowBounds);

    List<SwCustomGroupFavorite> selectByExample(SwCustomGroupFavoriteExample example);

    @Select({
        "select",
        "id, custom_group_id, operator, is_delete, create_time, datetime_lastchange",
        "from sw_custom_group_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCustomGroupFavoriteMapper.BaseResultMap")
    SwCustomGroupFavorite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwCustomGroupFavorite record, @Param("example") SwCustomGroupFavoriteExample example);

    int updateByExample(@Param("record") SwCustomGroupFavorite record, @Param("example") SwCustomGroupFavoriteExample example);

    int updateByPrimaryKeySelective(SwCustomGroupFavorite record);

    @Update({
        "update sw_custom_group_favorite",
        "set custom_group_id = #{customGroupId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroupFavorite record);
}