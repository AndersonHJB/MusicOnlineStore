/*
 * @Time    : 2025/3/14 12:35
 * @Author  : AI悦创
 * @FileName: User.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")  // 避免与 MySQL 保留关键字冲突
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    @Column(unique = true, nullable = false)
    private String username;

    // 密码
    private String password;

    // 是否为零售商
    private Boolean isRetailer;

    // 角色多对多关系
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, Boolean isRetailer) {
        this.username = username;
        this.password = password;
        this.isRetailer = isRetailer;
    }

    // Getter & Setter ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getIsRetailer() { return isRetailer; }
    public void setIsRetailer(Boolean isRetailer) { this.isRetailer = isRetailer; }

    public List<Role> getRoles() { return roles; }
    public void setRoles(List<Role> roles) { this.roles = roles; }
}
