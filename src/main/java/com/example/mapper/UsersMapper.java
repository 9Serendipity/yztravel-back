package com.example.mapper;

import com.example.tables.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//在运行时，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IOC容器
@Repository
public interface UsersMapper {



    //登录验证
    @Select("select * from users where phone = #{phone} and password = #{password}")
    Users login(String phone, String password);

    // 新增的方法
    @Insert("insert into users(phone, password, name, avatar_url) values(#{phone}, #{password}, #{name}, #{avatarUrl})")
    int insert(Users user);
    
    @Select("select * from users")
    List<Users> selectAll();


    // 根据用户ID获取用户信息
    @Select("select * from users where usersId = #{usersId}")
    Users getUserById(Integer usersId);

    // 更新用户头像
    @Update("update users set avatar_url = #{avatarUrl} where usersId = #{usersId}")
    int updateAvatar(Integer usersId, String avatarUrl);

    // 更新用户信息
    int updateUser(Users user);
}

