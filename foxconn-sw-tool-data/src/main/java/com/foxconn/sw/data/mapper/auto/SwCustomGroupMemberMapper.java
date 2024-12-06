package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.data.entity.SwCustomGroupMemberExample;
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
public interface SwCustomGroupMemberMapper {
    @SelectProvider(type=SwCustomGroupMemberSqlProvider.class, method="countByExample")
    long countByExample(SwCustomGroupMemberExample example);

    @DeleteProvider(type=SwCustomGroupMemberSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCustomGroupMemberExample example);

    @Delete({
        "delete from sw_custom_group_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_custom_group_member (custom_group_id, member, ",
        "member_type, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{customGroupId,jdbcType=INTEGER}, #{member,jdbcType=VARCHAR}, ",
        "#{memberType,jdbcType=INTEGER}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroupMember record);

    @InsertProvider(type=SwCustomGroupMemberSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCustomGroupMember record);

    @SelectProvider(type=SwCustomGroupMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="member", property="member", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupMember> selectByExampleWithRowbounds(SwCustomGroupMemberExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCustomGroupMemberSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="member", property="member", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupMember> selectByExample(SwCustomGroupMemberExample example);

    @Select({
        "select",
        "id, custom_group_id, member, member_type, is_delete, create_time, datetime_lastchange",
        "from sw_custom_group_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="member", property="member", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_type", property="memberType", jdbcType=JdbcType.INTEGER),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCustomGroupMember selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCustomGroupMemberSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCustomGroupMember record, @Param("example") SwCustomGroupMemberExample example);

    @UpdateProvider(type=SwCustomGroupMemberSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCustomGroupMember record, @Param("example") SwCustomGroupMemberExample example);

    @UpdateProvider(type=SwCustomGroupMemberSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCustomGroupMember record);

    @Update({
        "update sw_custom_group_member",
        "set custom_group_id = #{customGroupId,jdbcType=INTEGER},",
          "member = #{member,jdbcType=VARCHAR},",
          "member_type = #{memberType,jdbcType=INTEGER},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroupMember record);
}