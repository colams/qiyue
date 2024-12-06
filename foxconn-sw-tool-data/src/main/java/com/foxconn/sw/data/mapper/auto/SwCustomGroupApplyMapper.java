package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroupApply;
import com.foxconn.sw.data.entity.SwCustomGroupApplyExample;
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
public interface SwCustomGroupApplyMapper {
    @SelectProvider(type=SwCustomGroupApplySqlProvider.class, method="countByExample")
    long countByExample(SwCustomGroupApplyExample example);

    @DeleteProvider(type=SwCustomGroupApplySqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCustomGroupApplyExample example);

    @Delete({
        "delete from sw_custom_group_apply",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_custom_group_apply (custom_group_id, apply_employee_no, ",
        "remark, status, ",
        "create_time, datetime_lastchange)",
        "values (#{customGroupId,jdbcType=INTEGER}, #{applyEmployeeNo,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroupApply record);

    @InsertProvider(type=SwCustomGroupApplySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwCustomGroupApply record);

    @SelectProvider(type=SwCustomGroupApplySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_employee_no", property="applyEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupApply> selectByExampleWithRowbounds(SwCustomGroupApplyExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCustomGroupApplySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_employee_no", property="applyEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCustomGroupApply> selectByExample(SwCustomGroupApplyExample example);

    @Select({
        "select",
        "id, custom_group_id, apply_employee_no, remark, status, create_time, datetime_lastchange",
        "from sw_custom_group_apply",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="custom_group_id", property="customGroupId", jdbcType=JdbcType.INTEGER),
        @Result(column="apply_employee_no", property="applyEmployeeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCustomGroupApply selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwCustomGroupApplySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCustomGroupApply record, @Param("example") SwCustomGroupApplyExample example);

    @UpdateProvider(type=SwCustomGroupApplySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCustomGroupApply record, @Param("example") SwCustomGroupApplyExample example);

    @UpdateProvider(type=SwCustomGroupApplySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCustomGroupApply record);

    @Update({
        "update sw_custom_group_apply",
        "set custom_group_id = #{customGroupId,jdbcType=INTEGER},",
          "apply_employee_no = #{applyEmployeeNo,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroupApply record);
}