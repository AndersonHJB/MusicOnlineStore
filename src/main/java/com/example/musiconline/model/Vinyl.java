/*
 * @Time    : 2025/3/14 17:30
 * @Author  : AI悦创
 * @FileName: Vinyl.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.model;

import javax.persistence.*;

@Entity
@Table(name="vinyls")
public class Vinyl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 艺术家
    private String artist;

    // 专辑/单曲/EP名称
    private String title;

    // 简要信息
    private String description;

    // 发行日期
    private String releaseDate;

    // 价格
    private Double price;

    // 上传该唱片的用户ID
    private Long userId;

    public Vinyl() {
    }

    public Vinyl(String artist, String title, String description, String releaseDate, Double price, Long userId) {
        this.artist = artist;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.price = price;
        this.userId = userId;
    }

    // Getter & Setter ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
