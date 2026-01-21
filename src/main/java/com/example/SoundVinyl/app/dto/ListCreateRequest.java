package com.example.SoundVinyl.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ListCreateRequest(
        @NotBlank String name,
        @Size(max = 1000) String description,
        boolean isPublic
) {}