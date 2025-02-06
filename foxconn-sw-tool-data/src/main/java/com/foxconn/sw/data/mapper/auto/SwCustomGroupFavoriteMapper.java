package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import com.foxconn.sw.data.entity.SwCustomGroupFavoriteExample;
import java.util.List;
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
public interface SwCustomGroupFavoriteMapper {
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

    @InsertProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCustomGroupFavorite record);

    @SelectProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupFavorite> selectByExampleWithRowbounds(SwCustomGroupFavoriteExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupFavorite> selectByExample(SwCustomGroupFavoriteExample example);

    @Select({
        "select",
        "id, custom_group_id, operator, is_delete, create_time, datetime_lastchange",
        "from sw_custom_group_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCustomGroupFavorite selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCustomGroupFavorite record, @Param("example") SwCustomGroupFavoriteExample example);

    @UpdateProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCustomGroupFavorite record, @Param("example") SwCustomGroupFavoriteExample example);

    @UpdateProvider(type=SwCustomGroupFavoriteSqlProvider.class, method="updateByPrimaryKeySelective")
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