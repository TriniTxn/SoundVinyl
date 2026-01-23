package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.domain.repository.AlbumListRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/u")
public class ProfileController {

    private final UserRepository userRepo;
    private final ReviewRepository reviewRepo;
    private final AlbumListRepository albumListRepo;

    @GetMapping("/{username}")
    public String profile(@PathVariable String username, Model model) {
        var user = userRepo.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviewRepo.findByAlbumIdOrderByCreatedAtDesc(Long.valueOf(user.getId())));
        model.addAttribute("lists", albumListRepo.findByOwnerIdOrderByCreatedAtDesc(Long.valueOf(user.getId())));

        return "profile";
    }
}
