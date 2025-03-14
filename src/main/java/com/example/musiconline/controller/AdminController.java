/*
 * @Time    : 2025/3/14 18:11
 * @Author  : AI悦创
 * @FileName: AdminController.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.controller;

import com.example.musiconline.model.User;
import com.example.musiconline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/panel")
    //@PreAuthorize("hasRole('ADMIN')") // 如果启用表达式
    public String showAdminPanel(Model model) {
        // 这里仅示例查询所有用户
        // 实际上可以添加更多审核逻辑
        List<User> users = userService.getAllUsers(); // 需要在UserService里加一个方法
        model.addAttribute("users", users);
        return "adminPanel";
    }
}
