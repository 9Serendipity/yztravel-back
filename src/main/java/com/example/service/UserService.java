package com.example.service;

import com.example.common.Result;
import com.example.tables.Users;

public interface UserService {
    // 用户登录
    Result login(String phone, String password);
    // 添加用户
    Result addUser(Users user);
    // 更新用户信息
    Result updateUser(Users user);
    // 删除用户
    Result deleteUser(Integer id);
    // 获取用户列表
    Result getUserList();
    // 根据ID获取用户
    Result getUserById(Integer id);
    // 更新用户头像
    Result updateAvatar(Integer id, String avatarUrl);
}