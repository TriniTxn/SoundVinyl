package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    @Query("select a from Album a " +
            "where lower(a.title) like lower(concat('%', :q, '%')) " +
            "   or lower(a.artist.name) like lower(concat('%', :q, '%'))")
    List<Album> search(@Param("q") String q);

    @Query("SELECT a " +
            "FROM Album a " +
            "JOIN FETCH a.artist " +
            "WHERE lower(a.title) LIKE lower(concat('%', :q, '%'))\n" +
            "OR lower(a.artist.name) LIKE lower(concat('%', :q, '%'))"
    )
    List<Album> searchWithArtist(@Param("q") String q);

    @Query("SELECT a FROM Album a JOIN FETCH a.artist ORDER BY a.ratingAvg DESC")
    List<Album> findAllWithArtist();

    @Query("SELECT a FROM Album a JOIN FETCH a.artist WHERE a.id = :id ")
    Optional<Album> findByIdWithArtist(@Param("id") Long id);

    @Query("SELECT r FROM Review r JOIN FETCH r.user WHERE r.album.id = :albumId ORDER BY r.createdAt DESC")
    List<Review> findByAlbumIdOrderByCreatedAtDesc(@Param("albumId") Long albumId);


    /* Stats (future)
        long countByAlbumId(Long albumId);
     */

    @Query("SELECT avg(r.rating) FROM Review r WHERE r.album.id = :albumId AND r.rating IS NOT null")
    Double averageRatingByAlbumId(@Param("albumId") Long albumId);
}
