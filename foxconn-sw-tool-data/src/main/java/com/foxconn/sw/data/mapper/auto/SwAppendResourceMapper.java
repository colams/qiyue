package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
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
public interface SwAppendResourceMapper {
    @SelectProvider(type=SwAppendResourceSqlProvider.class, method="countByExample")
    long countByExample(SwAppendResourceExample example);

    @DeleteProvider(type=SwAppendResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwAppendResourceExample example);

    @Delete({
        "delete from sw_append_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_append_resource (source_type, file_path, ",
        "datetime_lastchange)",
        "values (#{sourceType,jdbcType=INTEGER}, #{filePath,jdbcType=VARCHAR}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwAppendResource record);

    @InsertProvider(type=SwAppendResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwAppendResource record);

    @SelectProvider(type=SwAppendResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAppendResource> selectByExampleWithRowbounds(SwAppendResourceExample example, RowBounds rowBounds);

    @SelectProvider(type=SwAppendResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAppendResource> selectByExample(SwAppendResourceExample example);

    @Select({
        "select",
        "id, source_type, file_path, datetime_lastchange",
        "from sw_append_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="source_type", property="sourceType", jdbcType=JdbcType.INTEGER),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwAppendResource selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwAppendResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwAppendResource record, @Param("example") SwAppendResourceExample example);

    @UpdateProvider(type=SwAppendResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwAppendResource record, @Param("example") SwAppendResourceExample example);

    @UpdateProvider(type=SwAppendResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwAppendResource record);

    @Update({
        "update sw_append_resource",
        "set source_type = #{sourceType,jdbcType=INTEGER},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwAppendResource record);
}