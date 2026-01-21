package com.example.SoundVinyl.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    private Integer releaseYear;

    private String coverUrl;

    @Column(length=10) // ALBUM | EP | SINGLE
    private String type;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private Artist artist;

    @Column(nullable=false)
    private Double ratingAvg = 0.0;

    @Column(nullable=false)
    private Integer ratingCount = 0;

    @Column(unique=true)
    private String mbid;
}
