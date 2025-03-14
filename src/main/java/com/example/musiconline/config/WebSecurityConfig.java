/*
 * @Time    : 2025/3/14 17:56
 * @Author  : AI悦创
 * @FileName: WebSecurityConfig.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.musiconline.service.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // ========= 密码加密器 =========
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ========= 认证管理器 =========
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // ========= 配置自定义的UserDetailsService与密码加密 =========
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    // ========= 核心安全策略配置（已切换回 Session 方式）=========
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()

                // ========== 请求权限配置 ==========
                .authorizeRequests()
                // 对以下路径允许匿名访问
                .antMatchers("/", "/register", "/login", "/css/**", "/js/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 其他所有请求均需认证
                .anyRequest().authenticated()
                .and()

                // ========== 使用Form表单登录，而非JWT无状态 ==========
                .formLogin()
                .loginPage("/login")                  // 指定自定义登录页面
                .loginProcessingUrl("/login")         // 表单提交的URL
                .defaultSuccessUrl("/vinyl/search", true)  // 登录成功默认跳转
                .failureUrl("/login?error=true")      // 登录失败跳转
                .permitAll()
                .and()

                // ========== 启用注销功能 ==========
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()

                // ========== （可选）开启SESSION策略：如需始终创建Session可改用ALWAYS ==========
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    // ========= 放行静态资源 =========
    @Override
    public void configure(WebSecurity web) {
        // 这里可以忽略静态资源文件夹
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }
}
