package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupExample;
import java.util.List;
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
public interface SwCustomGroupMapper {
    @Insert({
        "insert into sw_custom_group (name, owner, ",
        "group_type, is_private, ",
        "description, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{name,jdbcType=VARCHAR}, #{owner,jdbcType=VARCHAR}, ",
        "#{groupType,jdbcType=INTEGER}, #{isPrivate,jdbcType=INTEGER}, ",
        "#{description,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroup record);

    @InsertProvider(type=SwCustomGroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCustomGroup record);

    @SelectProvider(type=SwCustomGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_type", property="groupType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_private", property="isPrivate", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroup> selectByExampleWithRowbounds(SwCustomGroupExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCustomGroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_type", property="groupType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_private", property="isPrivate", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroup> selectByExample(SwCustomGroupExample example);

    @Select({
        "select",
        "id, name, owner, group_type, is_private, description, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_custom_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="owner", property="owner", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_type", property="groupType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_private", property="isPrivate", jdbcType=JdbcType.INTEGER),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCustomGroup selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCustomGroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCustomGroup record, @Param("example") SwCustomGroupExample example);

    @UpdateProvider(type=SwCustomGroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCustomGroup record, @Param("example") SwCustomGroupExample example);

    @UpdateProvider(type=SwCustomGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCustomGroup record);

    @Update({
        "update sw_custom_group",
        "set name = #{name,jdbcType=VARCHAR},",
          "owner = #{owner,jdbcType=VARCHAR},",
          "group_type = #{groupType,jdbcType=INTEGER},",
          "is_private = #{isPrivate,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroup record);
}