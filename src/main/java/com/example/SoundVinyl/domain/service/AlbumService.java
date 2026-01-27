package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.app.dto.AlbumStats;
import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepo;
    private final ReviewRepository reviewRepository;

    public List<Album> search(String q) {
        if (q == null || q.isBlank()) {
            return albumRepo.findAllWithArtist();
        }
        return albumRepo.searchWithArtist(q);
    }

    public Album getOrThrow(Long id) {
        return albumRepo.findByIdWithArtist(id).orElseThrow(() -> new EntityNotFoundException("Album not found"));
    }

    public AlbumStats getAlbumStats(Long albumId) {
        Double avg = reviewRepository.findAverageRatingByAlbumId(albumId);
        Long count = reviewRepository.countByAlbumId(albumId);
                
        return new AlbumStats(
                avg != null ? avg : 0.0,
                count != null ? count : 0L
        );
    }
}
