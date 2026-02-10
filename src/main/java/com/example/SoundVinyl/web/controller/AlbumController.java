package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.app.dto.AlbumStatsDTO;
import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.service.AlbumService;
import com.example.SoundVinyl.domain.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public String albumDetail(@PathVariable Long id, Model model) {
        Album album = albumService.getOrThrow(id);
        AlbumStatsDTO stats = albumService.getAlbumStats(id);

        model.addAttribute("stats", stats);
        model.addAttribute("myReview", null);
        model.addAttribute("album", album);

        return "album";
    }
}
