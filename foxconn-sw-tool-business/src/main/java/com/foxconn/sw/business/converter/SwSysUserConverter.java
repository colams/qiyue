package com.foxconn.sw.business.converter;

import com.foxconn.sw.data.dto.entity.acount.UserDTO;
import com.foxconn.sw.data.entity.SwUser;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SwSysUserConverter {

    /**
     * @param sysUsers
     * @return
     */
    public static List<UserDTO> toUserDto(List<SwUser> sysUsers) {
        if (CollectionUtils.isEmpty(sysUsers)) {
            return Lists.newArrayList();
        }
        List<UserDTO> userDTOS = new ArrayList<>();
        sysUsers.forEach(sysUser -> {
            UserDTO userDto = new UserDTO();
            userDTOS.add(userDto);

            userDto.setUserName(sysUser.getUserName());
            userDto.setName(sysUser.getName());
            userDto.setPassword(sysUser.getPassword());
            userDto.setPhone(sysUser.getPhone());
            userDto.setEmail(sysUser.getEmail());
            userDto.setDeptId(sysUser.getDeptId());
        });

        return userDTOS;
    }

    /**
     * @param sysUser
     * @return
     */
    public static UserDTO toUserDto(SwUser sysUser) {
        if (Objects.isNull(sysUser)) {
            return null;
        }

        UserDTO userDto = new UserDTO();
        userDto.setId(sysUser.getId());
        userDto.setUserName(sysUser.getUserName());
        userDto.setName(sysUser.getName());
        userDto.setPassword(sysUser.getPassword());
        userDto.setPhone(sysUser.getPhone());
        userDto.setEmail(sysUser.getEmail());
        userDto.setDeptId(sysUser.getDeptId());
        return userDto;
    }

    /**
     * @param userDTO
     * @return
     */
    public static SwUser toSysUser(UserDTO userDTO) {
        SwUser sysUser = new SwUser();
        sysUser.setUserName(userDTO.getUserName());
        sysUser.setName(userDTO.getName());
        sysUser.setPassword(userDTO.getPassword());
        sysUser.setPhone(userDTO.getPhone());
        sysUser.setEmail(userDTO.getEmail());
        sysUser.setDeptId(userDTO.getDeptId());
        return sysUser;
    }
}
