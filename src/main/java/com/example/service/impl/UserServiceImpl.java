package com.example.service.impl;

import com.example.common.Result;
import com.example.mapper.UsersMapper;
import com.example.service.UserService;
import com.example.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Result login(String phone, String password) {
        Users user = usersMapper.login(phone, password);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户名或密码错误");
    }

    @Override
    public Result getUserById(Integer id) {
        Users user = usersMapper.getUserById(id);
        if (user != null) {
            return Result.success(user);
        }
        return Result.error("用户不存在");
    }

    @Override
    public Result updateAvatar(Integer id, String avatarUrl) {
        if (usersMapper.updateAvatar(id, avatarUrl) > 0) {
            return Result.success(avatarUrl);
        }
        return Result.error("更新头像失败");
    }

    @Override
    public Result addUser(Users user) {
        // TODO: 实现添加用户的逻辑
        return null;
    }

    @Override
    public Result updateUser(Users user) {
        if (user.getUsersId() == null) {  // 修改为 getUsersId
            return Result.error("用户ID不能为空");
        }
        
        // 检查用户是否存在
        Users existUser = usersMapper.getUserById(user.getUsersId());  // 修改为 getUsersId
        if (existUser == null) {
            return Result.error("用户不存在");
        }

        // 调用mapper更新用户信息
        int rows = usersMapper.updateUser(user);
        if (rows > 0) {
            // 更新成功，返回更新后的用户信息
            Users updatedUser = usersMapper.getUserById(user.getUsersId());  // 修改为 getUsersId
            return Result.success(updatedUser);
        }
        return Result.error("更新用户信息失败");
    }

    @Override
    public Result deleteUser(Integer id) {
        // TODO: 实现删除用户的逻辑
        return null;
    }

    @Override
    public Result getUserList() {
        // TODO: 实现获取用户列表的逻辑
        return null;
    }
}