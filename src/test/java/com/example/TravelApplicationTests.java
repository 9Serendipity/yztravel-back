package com.example;


import com.example.mapper.UsersMapper;
import com.example.tables.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TravelApplicationTests {

    @Autowired
    private UsersMapper usersMapper;

}