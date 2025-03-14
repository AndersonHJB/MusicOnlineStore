/*
 * @Time    : 2025/3/14 17:54
 * @Author  : AI悦创
 * @FileName: VinylService.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.service;

import com.example.musiconline.model.Vinyl;
import com.example.musiconline.repository.VinylRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VinylService {

    @Autowired
    private VinylRepository vinylRepository;

    public Vinyl addVinyl(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    public Vinyl updateVinyl(Vinyl vinyl) {
        return vinylRepository.save(vinyl);
    }

    public void deleteVinyl(Long id) {
        vinylRepository.deleteById(id);
    }

    public Vinyl getById(Long id) {
        return vinylRepository.findById(id).orElse(null);
    }

    public List<Vinyl> searchVinyl(String keyword) {
        return vinylRepository.findByArtistContainingOrTitleContaining(keyword, keyword);
    }
}

