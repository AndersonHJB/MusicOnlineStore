/*
 * @Time    : 2025/3/14 18:04
 * @Author  : AI悦创
 * @FileName: AuthController.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.controller;

import com.example.musiconline.config.JwtUtil;
import com.example.musiconline.model.AuthRequest;
import com.example.musiconline.model.User;
import com.example.musiconline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // 注册页面
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    // 处理注册
    @PostMapping("/register")
    public String doRegister(AuthRequest request, Model model) {
        try {
            userService.registerUser(
                    request.getUsername(),
                    request.getPassword(),
                    request.getIsRetailer()
            );
            model.addAttribute("msg", "注册成功，请登录！");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // 登录页面
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // 处理登录
    @PostMapping("/login")
    public String doLogin(AuthRequest request, Model model) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "认证失败，请重试");
            return "login";
        }
        // 生成JWT令牌
        String token = jwtUtil.generateToken(request.getUsername());
        // 这里示例将Token暂存到model，也可以写到Cookie、LocalStorage等
        model.addAttribute("jwtToken", token);

        // 登录成功后跳转到搜索页面（或者首页）
        return "redirect:/vinyl/search";
    }
}

