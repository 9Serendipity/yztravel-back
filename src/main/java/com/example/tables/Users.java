package com.example.tables;

import lombok.Data;

@Data
public class Users {
    private Integer usersId;  // 修改字段名
    private String phone;
    private String password;
    private String name;
    private String avatarUrl;
}
