package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAlbumIdOrderByCreatedAtDesc(Long albumId);
    Optional<Review> findByUserIdAndAlbumId(Long userId, Long albumId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);
}
