package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.data.entity.SwCustomGroupMemberExample;
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
public interface SwCustomGroupMemberMapper {
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

    int insertSelective(SwCustomGroupMember record);

    List<SwCustomGroupMember> selectByExampleWithRowbounds(SwCustomGroupMemberExample example, RowBounds rowBounds);

    List<SwCustomGroupMember> selectByExample(SwCustomGroupMemberExample example);

    @Select({
        "select",
        "id, custom_group_id, member, member_type, is_delete, create_time, datetime_lastchange",
        "from sw_custom_group_member",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCustomGroupMemberMapper.BaseResultMap")
    SwCustomGroupMember selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwCustomGroupMember record, @Param("example") SwCustomGroupMemberExample example);

    int updateByExample(@Param("record") SwCustomGroupMember record, @Param("example") SwCustomGroupMemberExample example);

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