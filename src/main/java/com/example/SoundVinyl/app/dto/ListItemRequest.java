package com.example.SoundVinyl.app.dto;

import jakarta.validation.constraints.NotNull;

public record ListItemRequest(
        @NotNull Long albumId,
        Integer position,
        String note
) {}
