package com.example.SoundVinyl.web.controller;


import com.example.SoundVinyl.domain.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AlbumService albumService;

    @GetMapping("/")
    public String home(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("albums", albumService.search(q));
        model.addAttribute("q", q == null ? "" : q);
        return "home";
    }
}
