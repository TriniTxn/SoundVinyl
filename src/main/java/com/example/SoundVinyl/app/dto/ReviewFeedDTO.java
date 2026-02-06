package com.example.SoundVinyl.app.dto;

import com.example.SoundVinyl.domain.model.Review;

import java.time.Instant;

public record ReviewFeedDTO(
        Long reviewId,
        Long albumId,
        String albumTitle,
        String albumCoverUrl,
        String username,
        Double rating,
        String text,
        Instant updatedAt
) {
    public static ReviewFeedDTO from(Review r) {
        return new ReviewFeedDTO(
                r.getId(),
                r.getAlbum().getId(),
                r.getAlbum().getTitle(),
                r.getAlbum().getCoverUrl(),
                r.getUser().getUsername(),
                r.getRating(),
                r.getText(),
                r.getUpdatedAt()
        );
    }
}
