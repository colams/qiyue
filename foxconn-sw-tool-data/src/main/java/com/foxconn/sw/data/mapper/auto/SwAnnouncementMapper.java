package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwAnnouncement;
import com.foxconn.sw.data.entity.SwAnnouncementExample;
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
public interface SwAnnouncementMapper {
    long countByExample(SwAnnouncementExample example);

    int deleteByExample(SwAnnouncementExample example);

    @Delete({
        "delete from sw_announcement",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_announcement (title, category, ",
        "expiry_date, status, ",
        "content, top, operator, ",
        "last_updater, resourceIds, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{title,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{expiryDate,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{top,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{lastUpdater,jdbcType=VARCHAR}, #{resourceids,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwAnnouncement record);

    int insertSelective(SwAnnouncement record);

    List<SwAnnouncement> selectByExampleWithRowbounds(SwAnnouncementExample example, RowBounds rowBounds);

    List<SwAnnouncement> selectByExample(SwAnnouncementExample example);

    @Select({
        "select",
        "id, title, category, expiry_date, status, content, top, operator, last_updater, ",
        "resourceIds, is_delete, create_time, datetime_lastchange",
        "from sw_announcement",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwAnnouncementMapper.BaseResultMap")
    SwAnnouncement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwAnnouncement record, @Param("example") SwAnnouncementExample example);

    int updateByExample(@Param("record") SwAnnouncement record, @Param("example") SwAnnouncementExample example);

    int updateByPrimaryKeySelective(SwAnnouncement record);

    @Update({
        "update sw_announcement",
        "set title = #{title,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "expiry_date = #{expiryDate,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "top = #{top,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "last_updater = #{lastUpdater,jdbcType=VARCHAR},",
          "resourceIds = #{resourceids,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwAnnouncement record);
}