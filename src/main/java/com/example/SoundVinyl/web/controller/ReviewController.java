package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.app.dto.ReviewRequest;
import com.example.SoundVinyl.app.dto.ReviewViewDTO;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private static final Long CURRENT_USER_ID = 1L;

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    @PostMapping
    public Review upsert(@Valid @RequestBody ReviewRequest revRequest) {
        return reviewService.upsertReview(CURRENT_USER_ID,
                revRequest.albumId(),
                revRequest.rating(),
                revRequest.text()
        );
    }

    @GetMapping
    public List<ReviewViewDTO> byAlbum(@RequestParam Long albumId) {
        return reviewRepository.findByAlbumIdOrderByCreatedAtDesc(albumId)
                .stream()
                .map(r -> new ReviewViewDTO(
                        r.getId(),
                        r.getUser().getUsername(),
                        avatarOf(r.getUser()),
                        r.getRating(),
                        r.getText(),
                        r.getCreatedAt(),
                        r.getUser().getId().equals(CURRENT_USER_ID)
                ))
                .toList();
    }

    private String avatarOf(User user) {
        return "https://ui-avatars.com/api/?name=" + user.getUsername();
    }
}
