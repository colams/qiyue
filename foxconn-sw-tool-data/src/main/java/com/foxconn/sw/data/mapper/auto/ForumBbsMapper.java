package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsExample;
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
public interface ForumBbsMapper {
    int deleteByExample(ForumBbsExample example);

    @Delete({
        "delete from forum_bbs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_bbs (project, title, ",
        "author_no, status, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{project,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{authorNo,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumBbs record);

    int insertSelective(ForumBbs record);

    List<ForumBbs> selectByExampleWithRowbounds(ForumBbsExample example, RowBounds rowBounds);

    List<ForumBbs> selectByExample(ForumBbsExample example);

    @Select({
        "select",
        "id, project, title, author_no, status, is_delete, create_time, datetime_lastchange",
        "from forum_bbs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.ForumBbsMapper.BaseResultMap")
    ForumBbs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumBbs record, @Param("example") ForumBbsExample example);

    int updateByExample(@Param("record") ForumBbs record, @Param("example") ForumBbsExample example);

    int updateByPrimaryKeySelective(ForumBbs record);

    @Update({
        "update forum_bbs",
        "set project = #{project,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumBbs record);
}