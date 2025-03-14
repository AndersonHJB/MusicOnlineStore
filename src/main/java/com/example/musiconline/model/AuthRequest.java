/*
 * @Time    : 2025/3/14 17:51
 * @Author  : AI悦创
 * @FileName: AuthRequest.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.model;

public class AuthRequest {
    private String username;
    private String password;
    private Boolean isRetailer; // 注册时可选

    public AuthRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsRetailer() {
        return isRetailer;
    }

    public void setIsRetailer(Boolean isRetailer) {
        this.isRetailer = isRetailer;
    }
}
