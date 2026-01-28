package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private AlbumRepository albumRepo;

    @Autowired
    private UserRepository userRepo;

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
        album.setRatingCount((int) count);

        albumRepo.save(album);
    }
}
