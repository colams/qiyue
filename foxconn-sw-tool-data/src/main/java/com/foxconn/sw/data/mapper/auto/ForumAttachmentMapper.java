package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.ForumAttachment;
import com.foxconn.sw.data.entity.ForumAttachmentExample;
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
public interface ForumAttachmentMapper {
    int deleteByExample(ForumAttachmentExample example);

    @Delete({
        "delete from forum_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into forum_attachment (fb_id, comment_id, ",
        "resource_id, is_delete, ",
        "create_time, datatime_lastchange)",
        "values (#{fbId,jdbcType=INTEGER}, #{commentId,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datatimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ForumAttachment record);

    int insertSelective(ForumAttachment record);

    List<ForumAttachment> selectByExampleWithRowbounds(ForumAttachmentExample example, RowBounds rowBounds);

    List<ForumAttachment> selectByExample(ForumAttachmentExample example);

    @Select({
        "select",
        "id, fb_id, comment_id, resource_id, is_delete, create_time, datatime_lastchange",
        "from forum_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.ForumAttachmentMapper.BaseResultMap")
    ForumAttachment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ForumAttachment record, @Param("example") ForumAttachmentExample example);

    int updateByExample(@Param("record") ForumAttachment record, @Param("example") ForumAttachmentExample example);

    int updateByPrimaryKeySelective(ForumAttachment record);

    @Update({
        "update forum_attachment",
        "set fb_id = #{fbId,jdbcType=INTEGER},",
          "comment_id = #{commentId,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datatime_lastchange = #{datatimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ForumAttachment record);
}