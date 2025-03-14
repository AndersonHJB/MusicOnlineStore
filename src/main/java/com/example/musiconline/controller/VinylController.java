/*
 * @Time    : 2025/3/14 18:11
 * @Author  : AI悦创
 * @FileName: VinylController.java
 * @Software: IntelliJ IDEA
 * @Version: V1.0
 * @Blog    : https://bornforthis.cn/
 * Code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
 */
package com.example.musiconline.controller;

import com.example.musiconline.model.User;
import com.example.musiconline.model.Vinyl;
import com.example.musiconline.service.UserService;
import com.example.musiconline.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vinyl")
public class VinylController {

    @Autowired
    private VinylService vinylService;

    @Autowired
    private UserService userService;

    // 搜索页面
    @GetMapping("/search")
    public String showSearchPage() {
        return "vinylSearch";
    }

    // Ajax搜索接口
    @GetMapping("/searchAjax")
    @ResponseBody
    public List<Vinyl> searchVinylAjax(@RequestParam("keyword") String keyword) {
        return vinylService.searchVinyl(keyword);
    }

    // 详情页面
    @GetMapping("/detail/{id}")
    public String showVinylDetail(@PathVariable("id") Long id, Model model) {
        Vinyl vinyl = vinylService.getById(id);
        if (vinyl == null) {
            model.addAttribute("error", "未找到该唱片信息");
            return "vinylDetail";
        }
        model.addAttribute("vinyl", vinyl);
        return "vinylDetail";
    }

    // 用户添加新的Vinyl页面
    @GetMapping("/add")
    public String addVinylForm() {
        return "vinylDetail"; // 可以复用同一个页面，也可以写一个单独的表单页面
    }

    // 处理保存（添加或更新）
    @PostMapping("/save")
    public String saveVinyl(Vinyl vinyl) {
        // 获取当前用户
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        // 如果是新对象，设置userId
        if (vinyl.getId() == null) {
            vinyl.setUserId(user.getId());
        } else {
            // 如果是更新，确认权限(简化处理，实际可以做更多校验)
            Vinyl existing = vinylService.getById(vinyl.getId());
            if (existing != null && !existing.getUserId().equals(user.getId())) {
                throw new RuntimeException("没有权限修改别人的唱片信息！");
            }
        }
        vinylService.addVinyl(vinyl);
        return "redirect:/vinyl/search";
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String deleteVinyl(@PathVariable("id") Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        Vinyl existing = vinylService.getById(id);
        if (existing != null && existing.getUserId().equals(user.getId())) {
            vinylService.deleteVinyl(id);
        } else {
            throw new RuntimeException("没有权限删除该唱片！");
        }
        return "redirect:/vinyl/search";
    }
}

