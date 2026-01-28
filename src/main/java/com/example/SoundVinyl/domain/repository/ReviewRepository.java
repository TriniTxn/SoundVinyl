package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUserIdAndAlbumId(Long userId, Long albumId);

    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.album.id = :albumId ORDER BY r.createdAt DESC")
    List<Review> findByAlbumIdOrderByCreatedAtDesc(@Param("albumId") Long albumId);

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
