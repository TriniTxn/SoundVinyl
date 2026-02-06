package com.example.SoundVinyl.app.dto;

import com.example.SoundVinyl.domain.model.Review;

import java.time.Instant;

public record ReviewViewDTO(
        Long id,
        String username,
        String avatarUrl,
        Double rating,
        String text,
        Instant createdAt,
        boolean mine
) {
    public static ReviewViewDTO from(Review r, Long currentUserId) {
        return new ReviewViewDTO(
                r.getId(),
                r.getUser().getUsername(),
                r.getUser().getAvatarUrl(), // ou fallback
                r.getRating(),
                r.getText(),
                r.getCreatedAt(),
                r.getUser().getId().equals(currentUserId)
        );
    }
}

