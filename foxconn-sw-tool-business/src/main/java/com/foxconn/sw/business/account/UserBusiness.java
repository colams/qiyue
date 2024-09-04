package com.foxconn.sw.business.account;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.data.constants.enums.retcode.AccountExceptionCode;
import com.foxconn.sw.data.dto.entity.acount.EmployeeParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.acount.UserParams;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.acount.SwUserExtensionMapper;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    SwUserExtensionMapper sysUserExtensionMapper;
    @Autowired
    SwAppendResourceBusiness resourceBusiness;

    public SwUser save(SwUser swUser) {
        boolean result = sysUserExtensionMapper.insertSelective(swUser) > 0;
        if (!result) {
            throw new BizException(AccountExceptionCode.CREATE_ACCOUNT_EXCEPTION);
        }
        return swUser;
    }

    public SwUser queryUser(String employeeNo) {
        SwUserExample example = new SwUserExample();
        SwUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeNoEqualTo(employeeNo);
        List<SwUser> users = sysUserExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    public List<UserInfo> list(UserParams data) {
        return sysUserExtensionMapper.queryUserInfos(data);
    }

    public UserInfo queryUserInfo(String employeeID) {
        UserInfo userInfo = sysUserExtensionMapper.queryUserInfo(employeeID);
        userInfo.setDepartName(ZhConverterUtil.convertToTraditional(userInfo.getDepartName()));
        userInfo.setAvatar(resourceBusiness.getResourceUrl(userInfo.getAvatarID()));
        return userInfo;
    }

    public List<EmployeeVo> queryEmployees(EmployeeParams employeeParams) {
        return sysUserExtensionMapper.queryEmployees(employeeParams);
    }

    public boolean update(SwUser user) {
        SwUser swUser = new SwUser();
        swUser.setId(user.getId());
        swUser.setSolt(user.getSolt());
        swUser.setPassword(user.getPassword());
        swUser.setSignature(user.getSignature());
        boolean result = sysUserExtensionMapper.updateByPrimaryKeySelective(user) > 0;
        if (!result) {
            throw new BizException(AccountExceptionCode.RESET_PASSWORD_EXCEPTION);
        }
        return true;
    }

    public int update(String employeeNo, Integer resourceID) {
        return sysUserExtensionMapper.updateAvatarId(employeeNo, resourceID);
    }
}
