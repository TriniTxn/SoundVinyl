package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ReviewRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepo;
    private final AlbumRepository albumRepo;
    private final UserRepository userRepo;

    public Review upsertReview(Long userId, Long albumId, Double rating, String text) {
        User user = userRepo.findById(userId).orElseThrow();
        Album album = albumRepo.findById(albumId).orElseThrow();

        var existing = reviewRepo.findByUserIdAndAlbumId(userId, albumId);
        Review r = existing.orElseGet(Review::new);
        boolean isNew = r.getId() == null;

        r.setUser(user); r.setAlbum(album);
        r.setRating(rating); r.setText(text);
        r.setUpdatedAt(Instant.now());
        r = reviewRepo.save(r);

        var reviews = reviewRepo.findByAlbumIdOrderByCreatedAtDesc(albumId);
        double avg = reviews.stream().map(Review::getRating).filter(v -> v != null).mapToDouble(Double::doubleValue).average().orElse(0.0);
        album.setRatingAvg(Math.round(avg * 10.0) / 10.0);
        album.setRatingCount(reviews.size());
        albumRepo.save(album);

        return r;
    }
}
