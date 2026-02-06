package com.example.SoundVinyl.mapper;

import com.example.SoundVinyl.app.dto.ReviewViewDTO;
import com.example.SoundVinyl.domain.model.Review;
import com.example.SoundVinyl.domain.model.User;

public class ReviewMapper {

    private static final String DEFAULT_AVATAR = "/img/avatar-defaultt.jpg";

    public static ReviewViewDTO toView(Review review, User currentUser) {
        var user = review.getUser();

        return new ReviewViewDTO(
                review.getId(),
                user.getUsername(),
                user.getAvatarUrl() != null ? user.getAvatarUrl() : DEFAULT_AVATAR,
                review.getRating(),
                review.getText(),
                review.getCreatedAt(),
                currentUser != null && user.getId().equals(currentUser.getId())
        );
    }
}
