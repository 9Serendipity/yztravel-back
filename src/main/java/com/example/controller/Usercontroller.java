package com.example.controller;

import com.example.service.UserService;  // 添加这行导入
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;  // 添加这个导入
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.common.Result;
import com.example.mapper.UsersMapper;
import com.example.tables.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;  // 添加这个导入

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam String phone, @RequestParam String password) {
        return userService.login(phone, password);
    }

    @PostMapping("/info")
    public Result getUserInfo(@RequestBody Users userInfo) {
        return userService.getUserById(userInfo.getUsersId());
    }

    @PostMapping("/upload-avatar")
    public Result uploadAvatar(@RequestParam Integer usersId, @RequestParam MultipartFile file) {  // 修改参数名
        try {
            // 确保上传目录存在
            String uploadDir = "d:/codes/java/YZtravel/uploads/avatars/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 生成文件名
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + fileName;

            // 保存文件
            file.transferTo(new File(filePath));

            // 更新头像URL
            String avatarUrl = "/api/uploads/avatars/" + fileName;
            return userService.updateAvatar(usersId, avatarUrl);  // 修改参数名
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传文件失败");
        }
    }

    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody Users user) {
        return userService.updateUser(user);
    }
}
