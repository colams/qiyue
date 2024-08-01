package com.foxconn.sw.business.auth;

import com.foxconn.sw.business.converter.SwSysUserConverter;
import com.foxconn.sw.data.dto.entity.acount.UserDTO;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.data.entity.SwUserExample;
import com.foxconn.sw.data.mapper.extension.acount.SwUserExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserBusiness {

    @Autowired
    SwUserExtensionMapper sysUserExtensionMapper;

    public SwUser save(UserDTO userDTO) {
        SwUser sysUser = SwSysUserConverter.toSysUser(userDTO);
        sysUserExtensionMapper.insertSelective(sysUser);
        return sysUser;
    }

    public SwUser queryUser(String userName) {
        SwUserExample example = new SwUserExample();
        SwUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<SwUser> users = sysUserExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    public List<UserDTO> list() {
        List<SwUser> sysUsers = sysUserExtensionMapper.selectByExample(null);
        return SwSysUserConverter.toUserDto(sysUsers);
    }


    public UserDTO detail(Integer data) {
        SwUserExample example = new SwUserExample();
        SwUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(data);
        List<SwUser> sysUsers = sysUserExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(sysUsers) ? null : SwSysUserConverter.toUserDto(sysUsers.get(0));
    }
}
