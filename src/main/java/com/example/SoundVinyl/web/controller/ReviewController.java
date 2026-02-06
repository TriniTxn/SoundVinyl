package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.app.dto.ReviewRequestDTO;
import com.example.SoundVinyl.app.dto.ReviewViewDTO;
import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.service.ReviewService;
import com.example.SoundVinyl.mapper.ReviewMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private static final Long CURRENT_USER_ID = 1L;

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final AlbumRepository albumRepository;

    @PostMapping
    public Review upsert(@Valid @RequestBody ReviewRequestDTO revRequest) {
        return reviewService.upsertReview(CURRENT_USER_ID,
                revRequest.albumId(),
                revRequest.rating(),
                revRequest.text()
        );
    }

    @GetMapping
    public List<ReviewViewDTO> listByAlbum(@RequestParam Long albumId) {
        Album album = albumRepository.findById(albumId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User currentUser = null;

        return reviewRepository.findByAlbumIdOrderByUpdatedAtDesc(albumId)
                .stream()
                .map(r -> ReviewMapper.toView(r, currentUser))
                .toList();
    }

    private String avatarOf(User user) {
        return "https://ui-avatars.com/api/?name=" + user.getUsername();
    }


}
