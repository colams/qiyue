package com.foxconn.sw.business.account;

import com.foxconn.sw.data.entity.SwUserLogin;
import com.foxconn.sw.data.entity.SwUserLoginExample;
import com.foxconn.sw.data.mapper.extension.acount.SwUserLoginExtensionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class UserLoginBusiness {

    @Resource
    SwUserLoginExtensionMapper userLoginExtensionMapper;

    /**
     * 保存用户登录态
     *
     * @param employeeId
     * @param token
     * @param expireTime
     * @return
     */
    public boolean saveUserLogin(String employeeId, String token, LocalDateTime expireTime) {
        SwUserLogin userLogin = new SwUserLogin();
        userLogin.setEmployeeNo(employeeId);
        userLogin.setToken(token);
        userLogin.setExpireTime(expireTime);
        int count = userLoginExtensionMapper.insertSelective(userLogin);
        return count > 0;
    }


    /**
     * 根据用户名 查询有效的登录态
     *
     * @param employeeID
     * @param now
     * @return
     */
    public SwUserLogin queryLoginStateByName(String employeeID, LocalDateTime now) {
        SwUserLogin login = userLoginExtensionMapper.queryLoginStateByName(employeeID, now);
        if (Objects.nonNull(login)) {
            if (login.getExpireTime().compareTo(now.plusHours(2)) <= 0) {
                login.setExpireTime(now.plusHours(12));
                updateExpireTime(login.getId(), login.getExpireTime());
            }
        }
        return login;
    }

    /**
     * 更新登录态失效时间
     *
     * @param id
     * @param expireTime
     * @return
     */
    public boolean updateExpireTime(int id, LocalDateTime expireTime) {
        SwUserLogin newLogin = new SwUserLogin();
        newLogin.setId(id);
        newLogin.setExpireTime(expireTime);
        return userLoginExtensionMapper.updateByPrimaryKeySelective(newLogin) > 0;
    }

    public SwUserLogin queryLoginUser(String token) {
        SwUserLoginExample example = new SwUserLoginExample();
        SwUserLoginExample.Criteria criteria = example.createCriteria();
        criteria.andTokenEqualTo(token);
        criteria.andExpireTimeGreaterThan(LocalDateTime.now());
        List<SwUserLogin> userLogins = userLoginExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(userLogins) ? null : userLogins.get(0);
    }


}
