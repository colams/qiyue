package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwChangeLog;
import com.foxconn.sw.data.entity.SwChangeLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwChangeLogMapper {
    @Insert({
        "insert into sw_change_log (release_note, release_version, ",
        "create_time, operator, ",
        "last_updater, datetime_lastchange)",
        "values (#{releaseNote,jdbcType=VARCHAR}, #{releaseVersion,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{operator,jdbcType=VARCHAR}, ",
        "#{lastUpdater,jdbcType=VARCHAR}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwChangeLog record);

    int insertSelective(SwChangeLog record);

    List<SwChangeLog> selectByExampleWithRowbounds(SwChangeLogExample example, RowBounds rowBounds);

    List<SwChangeLog> selectByExample(SwChangeLogExample example);

    @Select({
        "select",
        "id, release_note, release_version, create_time, operator, last_updater, datetime_lastchange",
        "from sw_change_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwChangeLogMapper.BaseResultMap")
    SwChangeLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwChangeLog record, @Param("example") SwChangeLogExample example);

    int updateByExample(@Param("record") SwChangeLog record, @Param("example") SwChangeLogExample example);

    int updateByPrimaryKeySelective(SwChangeLog record);

    @Update({
        "update sw_change_log",
        "set release_note = #{releaseNote,jdbcType=VARCHAR},",
          "release_version = #{releaseVersion,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "last_updater = #{lastUpdater,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwChangeLog record);
}