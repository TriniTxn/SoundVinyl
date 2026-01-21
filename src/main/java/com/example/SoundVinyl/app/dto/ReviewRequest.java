package com.example.SoundVinyl.app.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewRequest(
        @NotNull Long albumId,
        @DecimalMin("0.0") @DecimalMax("5.0") Double rating,
        @Size(max=5000) String text
) {}