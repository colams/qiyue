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
        "insert into sw_append_resource (origin_name, file_path, ",
        "upload_type, operator, ",
        "size, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{originName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{uploadType,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{size,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwAppendResource record);

    @InsertProvider(type=SwAppendResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwAppendResource record);

    @SelectProvider(type=SwAppendResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="origin_name", property="originName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_type", property="uploadType", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAppendResource> selectByExampleWithRowbounds(SwAppendResourceExample example, RowBounds rowBounds);

    @SelectProvider(type=SwAppendResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="origin_name", property="originName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_type", property="uploadType", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwAppendResource> selectByExample(SwAppendResourceExample example);

    @Select({
        "select",
        "id, origin_name, file_path, upload_type, operator, size, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_append_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="origin_name", property="originName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_type", property="uploadType", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="size", property="size", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
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
        "set origin_name = #{originName,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "upload_type = #{uploadType,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "size = #{size,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwAppendResource record);
}