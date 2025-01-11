package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwAnnouncement;
import com.foxconn.sw.data.entity.SwAnnouncementExample;
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
public interface SwAnnouncementMapper {
    @SelectProvider(type=SwAnnouncementSqlProvider.class, method="countByExample")
    long countByExample(SwAnnouncementExample example);

    @DeleteProvider(type=SwAnnouncementSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwAnnouncementExample example);

    @Delete({
        "delete from sw_announcement",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_announcement (title, expiry_date, ",
        "status, content, ",
        "is_delete, operator, ",
        "last_updater, create_time, ",
        "datetime_lastchange)",
        "values (#{title,jdbcType=VARCHAR}, #{expiryDate,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{lastUpdater,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwAnnouncement record);

    @InsertProvider(type=SwAnnouncementSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwAnnouncement record);

    @SelectProvider(type=SwAnnouncementSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="expiry_date", property="expiryDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAnnouncement> selectByExampleWithRowbounds(SwAnnouncementExample example, RowBounds rowBounds);

    @SelectProvider(type=SwAnnouncementSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="expiry_date", property="expiryDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAnnouncement> selectByExample(SwAnnouncementExample example);

    @Select({
        "select",
        "id, title, expiry_date, status, content, is_delete, operator, last_updater, ",
        "create_time, datetime_lastchange",
        "from sw_announcement",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="expiry_date", property="expiryDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_updater", property="lastUpdater", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwAnnouncement selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwAnnouncementSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwAnnouncement record, @Param("example") SwAnnouncementExample example);

    @UpdateProvider(type=SwAnnouncementSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwAnnouncement record, @Param("example") SwAnnouncementExample example);

    @UpdateProvider(type=SwAnnouncementSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwAnnouncement record);

    @Update({
        "update sw_announcement",
        "set title = #{title,jdbcType=VARCHAR},",
          "expiry_date = #{expiryDate,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "last_updater = #{lastUpdater,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwAnnouncement record);
}