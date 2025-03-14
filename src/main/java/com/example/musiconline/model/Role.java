/*
 * @Time    : 2025/3/14 12:36
 * @Author  : AI悦创
 * @FileName: Role.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.model;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName; // e.g. "ROLE_USER", "ROLE_ADMIN"

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}
