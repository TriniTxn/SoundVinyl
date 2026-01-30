package com.example.SoundVinyl.app.dto;

import java.time.Instant;

public record ReviewResponseDTO(
        Long id,
        String username,
        String avatarUrl,
        Double rating,
        String text,
        Instant createdAt,
        Instant updatedAt,
        boolean mine
) {
}
