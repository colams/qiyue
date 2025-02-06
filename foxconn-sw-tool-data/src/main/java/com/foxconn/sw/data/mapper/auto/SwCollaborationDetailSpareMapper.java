package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpareExample;
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
public interface SwCollaborationDetailSpareMapper {
    @DeleteProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCollaborationDetailSpareExample example);

    @Delete({
        "delete from sw_collaboration_detail_spare",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail_spare (task_id, detail_id, ",
        "operator, value, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{taskId,jdbcType=INTEGER}, #{detailId,jdbcType=BIGINT}, ",
        "#{operator,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetailSpare record);

    @InsertProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwCollaborationDetailSpare record);

    @SelectProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationDetailSpare> selectByExampleWithRowbounds(SwCollaborationDetailSpareExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationDetailSpare> selectByExample(SwCollaborationDetailSpareExample example);

    @Select({
        "select",
        "id, task_id, detail_id, operator, value, is_delete, create_time, datetime_lastchange",
        "from sw_collaboration_detail_spare",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="task_id", property="taskId", jdbcType=JdbcType.INTEGER),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCollaborationDetailSpare selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCollaborationDetailSpare record, @Param("example") SwCollaborationDetailSpareExample example);

    @UpdateProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCollaborationDetailSpare record, @Param("example") SwCollaborationDetailSpareExample example);

    @UpdateProvider(type=SwCollaborationDetailSpareSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCollaborationDetailSpare record);

    @Update({
        "update sw_collaboration_detail_spare",
        "set task_id = #{taskId,jdbcType=INTEGER},",
          "detail_id = #{detailId,jdbcType=BIGINT},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetailSpare record);
}