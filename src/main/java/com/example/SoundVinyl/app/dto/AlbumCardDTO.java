package com.example.SoundVinyl.app.dto;

public record AlbumCardDTO(
        Long id,
        String title,
        String artistName,
        Integer releaseYear,
        String coverUrl,
        double avgRating,
        long ratingCount
) {
}
