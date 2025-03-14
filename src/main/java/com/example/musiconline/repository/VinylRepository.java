/*
 * @Time    : 2025/3/14 17:53
 * @Author  : AI悦创
 * @FileName: VinylRepository.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.repository;

import com.example.musiconline.model.Vinyl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VinylRepository extends JpaRepository<Vinyl, Long> {

    // 根据艺术家或标题的包含关系搜索
    List<Vinyl> findByArtistContainingOrTitleContaining(String artistKeyword, String titleKeyword);

}

