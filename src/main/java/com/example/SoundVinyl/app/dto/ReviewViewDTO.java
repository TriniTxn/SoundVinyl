package com.example.SoundVinyl.app.dto;

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
}
