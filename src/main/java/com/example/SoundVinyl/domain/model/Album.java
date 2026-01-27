package com.example.SoundVinyl.domain.model;

import com.example.SoundVinyl.domain.enums.AlbumType;
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

    @Enumerated(EnumType.STRING)
    private AlbumType type;

    @ManyToOne(optional=false, fetch = FetchType.LAZY)
    private Artist artist;

    @Column(name = "rating_avg")
    private Double ratingAvg;

    @Column(name = "rating_count")
    private Integer ratingCount;


    @Column(unique=true)
    private String mbid;
}
