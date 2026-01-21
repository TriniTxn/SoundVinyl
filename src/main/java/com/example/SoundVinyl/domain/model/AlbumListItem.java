package com.example.SoundVinyl.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private AlbumList list;

    @ManyToOne(optional=false)
    private Album album;

    private Integer position;

    @Column(length=500)
    private String note;
}
