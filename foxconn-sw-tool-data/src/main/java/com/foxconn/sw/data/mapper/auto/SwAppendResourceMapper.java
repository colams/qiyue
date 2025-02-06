package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwAppendResourceExample;
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
public interface SwAppendResourceMapper {
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

    int insertSelective(SwAppendResource record);

    List<SwAppendResource> selectByExampleWithRowbounds(SwAppendResourceExample example, RowBounds rowBounds);

    List<SwAppendResource> selectByExample(SwAppendResourceExample example);

    @Select({
        "select",
        "id, origin_name, file_path, upload_type, operator, size, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_append_resource",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwAppendResourceMapper.BaseResultMap")
    SwAppendResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwAppendResource record, @Param("example") SwAppendResourceExample example);

    int updateByExample(@Param("record") SwAppendResource record, @Param("example") SwAppendResourceExample example);

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