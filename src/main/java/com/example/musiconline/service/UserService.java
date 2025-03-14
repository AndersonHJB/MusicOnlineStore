/*
 * @Time    : 2025/3/14 17:54
 * @Author  : AI悦创
 * @FileName: UserService.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.service;

import com.example.musiconline.model.Role;
import com.example.musiconline.model.User;
import com.example.musiconline.repository.RoleRepository;
import com.example.musiconline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password, Boolean isRetailer) {
        // 检查用户是否已存在
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already taken!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsRetailer(isRetailer != null ? isRetailer : false);

        // 默认赋予ROLE_USER；如果需要给零售商额外角色，可自行扩展
        Role userRole = roleRepository.findByRoleName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        }

        // 如果是管理员注册，则也可赋予 ROLE_ADMIN
        // 此处仅演示，真正环境可自行控制
        // Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
        // if (adminRole == null) {
        //     adminRole = new Role("ROLE_ADMIN");
        //     roleRepository.save(adminRole);
        // }

        user.setRoles(Arrays.asList(userRole));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

