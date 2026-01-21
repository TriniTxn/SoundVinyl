package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameIgnoreCase(String name);
}
