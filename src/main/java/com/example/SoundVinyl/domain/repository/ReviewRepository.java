package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAlbumIdOrderByCreatedAtDesc(Long albumId);
    Optional<Review> findByUserIdAndAlbumId(Long userId, Long albumId);
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("""
    SELECT AVG(r.rating)
    FROM Review r
    WHERE r.album.id = :albumId
    """)
    Double findAverageRatingByAlbumId(Long albumId);

    @Query("""
    SELECT COUNT(r)
    FROM Review r
    WHERE r.album.id = :albumId
    """)
    Long countByAlbumId(Long albumId);
}
