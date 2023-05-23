package com.nuomi.shiro.service;

import com.nuomi.shiro.entity.User;

import java.util.List;

/**
 * @Author: zh
 * @Date: 2023-05-23-11:40
 * @Description:
 */
public interface UserService {
    //用户登录
    User getUserInfoByName(String name);

    List<String> getUserRoleInfo(String principal);

    List<String> getUserPermissionInfo(List<String> roles);
}
