package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample;
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
public interface SwDocumentPermissionMapper {
    @SelectProvider(type=SwDocumentPermissionSqlProvider.class, method="countByExample")
    long countByExample(SwDocumentPermissionExample example);

    @DeleteProvider(type=SwDocumentPermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwDocumentPermissionExample example);

    @Delete({
        "delete from sw_document_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_document_permission (document_id, permission_type, ",
        "permission_value, extra)",
        "values (#{documentId,jdbcType=INTEGER}, #{permissionType,jdbcType=INTEGER}, ",
        "#{permissionValue,jdbcType=VARCHAR}, #{extra,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocumentPermission record);

    @InsertProvider(type=SwDocumentPermissionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwDocumentPermission record);

    @SelectProvider(type=SwDocumentPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_type", property="permissionType", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_value", property="permissionValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR)
    })
    List<SwDocumentPermission> selectByExampleWithRowbounds(SwDocumentPermissionExample example, RowBounds rowBounds);

    @SelectProvider(type=SwDocumentPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_type", property="permissionType", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_value", property="permissionValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR)
    })
    List<SwDocumentPermission> selectByExample(SwDocumentPermissionExample example);

    @Select({
        "select",
        "id, document_id, permission_type, permission_value, extra",
        "from sw_document_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_type", property="permissionType", jdbcType=JdbcType.INTEGER),
        @Result(column="permission_value", property="permissionValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR)
    })
    SwDocumentPermission selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwDocumentPermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwDocumentPermission record, @Param("example") SwDocumentPermissionExample example);

    @UpdateProvider(type=SwDocumentPermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwDocumentPermission record, @Param("example") SwDocumentPermissionExample example);

    @UpdateProvider(type=SwDocumentPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwDocumentPermission record);

    @Update({
        "update sw_document_permission",
        "set document_id = #{documentId,jdbcType=INTEGER},",
          "permission_type = #{permissionType,jdbcType=INTEGER},",
          "permission_value = #{permissionValue,jdbcType=VARCHAR},",
          "extra = #{extra,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocumentPermission record);
}