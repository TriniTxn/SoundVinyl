package com.example.SoundVinyl.domain.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String country;

    private Integer formedIn;

    @Column(unique=true)
    private String mbid; // optional (MusicBrainz)
}
