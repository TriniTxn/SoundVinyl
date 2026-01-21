package com.example.SoundVinyl.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User user;

    @ManyToOne(optional=false)
    private Album album;

    @DecimalMin("0.0") @DecimalMax("5.0")
    private Double rating;

    @Column(length=5000)
    private String text;

    @Column(nullable=false, updatable=false)
    private Instant createdAt = Instant.now();

    private Instant updatedAt;
}
