package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.app.dto.ReviewFeedDTO;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReviewPageController {

    private final ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public String reviewsPage(Model model) {
        var reviews = reviewRepository.findRecentReviews()
                .stream()
                .map(ReviewFeedDTO::from)
                .toList();

        model.addAttribute("reviews", reviews);
        return "reviews";
    }
}
