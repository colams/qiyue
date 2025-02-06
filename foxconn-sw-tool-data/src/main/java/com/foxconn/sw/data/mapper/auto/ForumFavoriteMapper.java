package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumFavorite;
import com.foxconn.sw.data.entity.ForumFavoriteExample;
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
public interface ForumFavoriteMapper {
    @Insert({
        "insert into forum_favorite (operator, fb_id, ",
        "is_valid, create_time, ",
        "datatime_lastchange)",
        "values (#{operator,jdbcType=VARCHAR}, #{fbId,jdbcType=INTEGER}, ",
        "#{isValid,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datatimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumFavorite record);

    @InsertProvider(type=ForumFavoriteSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ForumFavorite record);

    @SelectProvider(type=ForumFavoriteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumFavorite> selectByExampleWithRowbounds(ForumFavoriteExample example, RowBounds rowBounds);

    @SelectProvider(type=ForumFavoriteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ForumFavorite> selectByExample(ForumFavoriteExample example);

    @Select({
        "select",
        "id, operator, fb_id, is_valid, create_time, datatime_lastchange",
        "from forum_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="fb_id", property="fbId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_valid", property="isValid", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datatime_lastchange", property="datatimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    ForumFavorite selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ForumFavoriteSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ForumFavorite record, @Param("example") ForumFavoriteExample example);

    @UpdateProvider(type=ForumFavoriteSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ForumFavorite record, @Param("example") ForumFavoriteExample example);

    @UpdateProvider(type=ForumFavoriteSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ForumFavorite record);

    @Update({
        "update forum_favorite",
        "set operator = #{operator,jdbcType=VARCHAR},",
          "fb_id = #{fbId,jdbcType=INTEGER},",
          "is_valid = #{isValid,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datatime_lastchange = #{datatimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumFavorite record);
}