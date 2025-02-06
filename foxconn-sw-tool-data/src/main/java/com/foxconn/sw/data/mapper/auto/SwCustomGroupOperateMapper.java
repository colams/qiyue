package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupOperate;
import com.foxconn.sw.data.entity.SwCustomGroupOperateExample;
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
public interface SwCustomGroupOperateMapper {
    int deleteByExample(SwCustomGroupOperateExample example);

    @Delete({
        "delete from sw_custom_group_operate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_custom_group_operate (custom_group_id, operator, ",
        "operate_type, remark, ",
        "is_read, status, ",
        "create_time, datetime_lastchange)",
        "values (#{customGroupId,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{operateType,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{isRead,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroupOperate record);

    int insertSelective(SwCustomGroupOperate record);

    List<SwCustomGroupOperate> selectByExampleWithRowbounds(SwCustomGroupOperateExample example, RowBounds rowBounds);

    List<SwCustomGroupOperate> selectByExample(SwCustomGroupOperateExample example);

    @Select({
        "select",
        "id, custom_group_id, operator, operate_type, remark, is_read, status, create_time, ",
        "datetime_lastchange",
        "from sw_custom_group_operate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCustomGroupOperateMapper.BaseResultMap")
    SwCustomGroupOperate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwCustomGroupOperate record, @Param("example") SwCustomGroupOperateExample example);

    int updateByExample(@Param("record") SwCustomGroupOperate record, @Param("example") SwCustomGroupOperateExample example);

    int updateByPrimaryKeySelective(SwCustomGroupOperate record);

    @Update({
        "update sw_custom_group_operate",
        "set custom_group_id = #{customGroupId,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "operate_type = #{operateType,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "is_read = #{isRead,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroupOperate record);
}