package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.domain.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    public String detail(Long id, Model model) {
        model.addAttribute("album", albumService.getOrThrow(id));
        return "album";
    }
}
