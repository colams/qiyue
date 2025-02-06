package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumBbsCommentExample;
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
public interface ForumBbsCommentMapper {
    int deleteByExample(ForumBbsCommentExample example);

    @Delete({
        "delete from forum_bbs_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_bbs_comment (fb_id, parent_id, ",
        "target_id, author_no, ",
        "is_delete, create_time, ",
        "datetime_lastchange, content)",
        "values (#{fbId,jdbcType=INTEGER}, #{parentId,jdbcType=INTEGER}, ",
        "#{targetId,jdbcType=INTEGER}, #{authorNo,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumBbsComment record);

    int insertSelective(ForumBbsComment record);

    List<ForumBbsComment> selectByExampleWithBLOBsWithRowbounds(ForumBbsCommentExample example, RowBounds rowBounds);

    List<ForumBbsComment> selectByExampleWithBLOBs(ForumBbsCommentExample example);

    List<ForumBbsComment> selectByExampleWithRowbounds(ForumBbsCommentExample example, RowBounds rowBounds);

    List<ForumBbsComment> selectByExample(ForumBbsCommentExample example);

    @Select({
        "select",
        "id, fb_id, parent_id, target_id, author_no, is_delete, create_time, datetime_lastchange, ",
        "content",
        "from forum_bbs_comment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.ForumBbsCommentMapper.ResultMapWithBLOBs")
    ForumBbsComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    int updateByExample(@Param("record") ForumBbsComment record, @Param("example") ForumBbsCommentExample example);

    int updateByPrimaryKeySelective(ForumBbsComment record);

    @Update({
        "update forum_bbs_comment",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "target_id = #{targetId,jdbcType=INTEGER},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ForumBbsComment record);

    @Update({
        "update forum_bbs_comment",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "target_id = #{targetId,jdbcType=INTEGER},",
          "author_no = #{authorNo,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumBbsComment record);
}