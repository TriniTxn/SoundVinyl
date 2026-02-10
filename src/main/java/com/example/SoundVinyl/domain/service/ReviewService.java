package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.app.dto.AlbumStatsDTO;
import com.example.SoundVinyl.app.dto.ReviewRequestDTO;
import com.example.SoundVinyl.app.dto.ReviewResponseDTO;
import com.example.SoundVinyl.app.dto.ReviewViewDTO;
import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import com.example.SoundVinyl.mapper.ReviewMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private AlbumRepository albumRepo;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AlbumService albumService;

    public Review upsertReview(Long userId, Long albumId, Double rating, String text) {

        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Album album = albumRepo.findById(albumId).orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Review review = reviewRepo
                .findByUserIdAndAlbumId(userId, albumId)
                .orElseGet(() -> Review.builder().user(user).album(album).createdAt(Instant.now()).build());

        review.setRating(rating);
        review.setText(text);
        review.setUpdatedAt(Instant.now());

        Review saved = reviewRepo.save(review);

        updateAlbumStats(album);

        return saved;
    }

    private void updateAlbumStats(Album album) {
        Double avg = reviewRepo.findAverageRatingByAlbumId(album.getId());
        long count = reviewRepo.countByAlbumId(album.getId());

        album.setRatingAvg(avg == null ? 0.0 : Math.round(avg * 10.0) / 10.0);
        album.setRatingCount((long) (int) count);

        albumRepo.save(album);
    }

    @Transactional
    public Review createReview(ReviewRequestDTO reviewRequestDTO) {

        Album album = albumRepo.findById(reviewRequestDTO.albumId()).orElseThrow(() -> new RuntimeException("Album not found"));

        User user = userRepo.findById(reviewRequestDTO.userId()).orElseThrow(() -> new RuntimeException("User not found"));

        Review review = Review.builder()
                .album(album)
                .user(user)
                .rating(reviewRequestDTO.rating())
                .text(reviewRequestDTO.text())
                .build();

        Review savedReview = reviewRepo.save(review);

        albumService.updateAlbumStats(album.getId());

        return savedReview;
    }

    @Transactional
    public Review updateReview(Long reviewId, ReviewRequestDTO dto) {

        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRating(dto.rating());
        review.setText(dto.text());

        Review updated = reviewRepo.save(review);

        albumService.updateAlbumStats(review.getAlbum().getId());

        return updated;
    }

    @Transactional
    public void deleteReview(Long reviewId) {

        Review review = reviewRepo.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));

        Long albumId = review.getAlbum().getId();

        reviewRepo.delete(review);

        albumService.updateAlbumStats(albumId);
    }

    public Review getReviewById(Long id) {
        return reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<ReviewViewDTO> listByAlbum(Long albumId) {
        Album album = albumRepo.findById(albumId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User currentUser = null;

        return reviewRepo.findByAlbumIdOrderByUpdatedAtDesc(albumId)
                .stream()
                .map(r -> ReviewMapper.toView(r, currentUser))
                .toList();
    }
}
