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
    public String create(@RequestBody ReviewRequestDTO requestDto) {
        reviewService.createReview(requestDto);
        return "Review created successfully";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody ReviewRequestDTO requestDTO) {
        reviewService.updateReview(id, requestDTO);
        return "Review update successfully";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }

    @GetMapping("/{id}")
    public Object getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }


    @GetMapping
    public List<ReviewViewDTO> listByAlbum(@RequestParam Long albumId) {
        return reviewService.listByAlbum(albumId);
    }

    private String avatarOf(User user) {
        return "https://ui-avatars.com/api/?name=" + user.getUsername();
    }


}
