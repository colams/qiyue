package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupOperate;
import com.foxconn.sw.data.entity.SwCustomGroupOperateExample;
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
public interface SwCustomGroupOperateMapper {
    @DeleteProvider(type=SwCustomGroupOperateSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwCustomGroupOperateSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCustomGroupOperate record);

    @SelectProvider(type=SwCustomGroupOperateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupOperate> selectByExampleWithRowbounds(SwCustomGroupOperateExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCustomGroupOperateSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupOperate> selectByExample(SwCustomGroupOperateExample example);

    @Select({
        "select",
        "id, custom_group_id, operator, operate_type, remark, is_read, status, create_time, ",
        "datetime_lastchange",
        "from sw_custom_group_operate",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="operate_type", property="operateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_read", property="isRead", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCustomGroupOperate selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCustomGroupOperateSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCustomGroupOperate record, @Param("example") SwCustomGroupOperateExample example);

    @UpdateProvider(type=SwCustomGroupOperateSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCustomGroupOperate record, @Param("example") SwCustomGroupOperateExample example);

    @UpdateProvider(type=SwCustomGroupOperateSqlProvider.class, method="updateByPrimaryKeySelective")
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