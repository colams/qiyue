package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumFavorite;
import com.foxconn.sw.data.entity.ForumFavoriteExample;
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
public interface ForumFavoriteMapper {
    int deleteByExample(ForumFavoriteExample example);

    @Delete({
        "delete from forum_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

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

    int insertSelective(ForumFavorite record);

    List<ForumFavorite> selectByExampleWithRowbounds(ForumFavoriteExample example, RowBounds rowBounds);

    List<ForumFavorite> selectByExample(ForumFavoriteExample example);

    @Select({
        "select",
        "id, operator, fb_id, is_valid, create_time, datatime_lastchange",
        "from forum_favorite",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.ForumFavoriteMapper.BaseResultMap")
    ForumFavorite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumFavorite record, @Param("example") ForumFavoriteExample example);

    int updateByExample(@Param("record") ForumFavorite record, @Param("example") ForumFavoriteExample example);

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