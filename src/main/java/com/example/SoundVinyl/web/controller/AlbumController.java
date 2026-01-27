package com.example.SoundVinyl.web.controller;

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

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public String albumDetail(@PathVariable Long id, Model model) {
        Album album = albumService.getOrThrow(id);
        /* var stats = albumService.getAlbumStats(id); */
        Optional<Review> myReview = Optional.empty();

        /* Later on will be loaded by logged user */
        model.addAttribute("myReview", myReview.orElse(null));
        model.addAttribute("album", album);
        /* model.addAttribute("stats", stats); */

        return "album";
    }
}
