package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample;
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
public interface SwDocumentPermissionMapper {
    int deleteByExample(SwDocumentPermissionExample example);

    @Delete({
        "delete from sw_document_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_document_permission (document_id, permission_type, ",
        "permission_value, extra, ",
        "is_delete)",
        "values (#{documentId,jdbcType=INTEGER}, #{permissionType,jdbcType=INTEGER}, ",
        "#{permissionValue,jdbcType=VARCHAR}, #{extra,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocumentPermission record);

    int insertSelective(SwDocumentPermission record);

    List<SwDocumentPermission> selectByExampleWithRowbounds(SwDocumentPermissionExample example, RowBounds rowBounds);

    List<SwDocumentPermission> selectByExample(SwDocumentPermissionExample example);

    @Select({
        "select",
        "id, document_id, permission_type, permission_value, extra, is_delete",
        "from sw_document_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwDocumentPermissionMapper.BaseResultMap")
    SwDocumentPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwDocumentPermission record, @Param("example") SwDocumentPermissionExample example);

    int updateByExample(@Param("record") SwDocumentPermission record, @Param("example") SwDocumentPermissionExample example);

    int updateByPrimaryKeySelective(SwDocumentPermission record);

    @Update({
        "update sw_document_permission",
        "set document_id = #{documentId,jdbcType=INTEGER},",
          "permission_type = #{permissionType,jdbcType=INTEGER},",
          "permission_value = #{permissionValue,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocumentPermission record);
}