package com.example.SoundVinyl.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private User owner;

    @Column(nullable=false)
    private String name;

    @Column(length=1000)
    private String description;

    @Column(nullable=false)
    private boolean isPublic = true;

    @OneToMany(mappedBy="list", cascade=CascadeType.ALL, orphanRemoval=true)
    @OrderBy("position ASC")
    @Builder.Default
    private List<AlbumListItem> items = new ArrayList<>();

    private Instant createdAt = Instant.now();
}
