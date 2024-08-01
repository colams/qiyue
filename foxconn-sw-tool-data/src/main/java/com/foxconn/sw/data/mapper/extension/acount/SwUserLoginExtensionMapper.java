package com.foxconn.sw.data.mapper.extension.acount;

import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.mapper.auto.SwUserLoginMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SwUserLoginExtensionMapper extends SwUserLoginMapper {

    @Select("select * from sw_user_login " +
            "where user_name=#{userName,jdbcType=VARCHAR} " +
            "and expire_time>#{expireTime,jdbcType=TIMESTAMP} " +
            "order by expire_time desc " +
            "limit 1 "
    )
    SwUserLogin queryLoginStateByName(@Param("userName") String userName, @Param("expireTime") LocalDateTime expireTime);


}
