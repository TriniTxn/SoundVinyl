package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.domain.model.Album;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepo;

    public List<Album> search(String q) {
        return (q == null || q.isBlank()) ? albumRepo.findAll() : albumRepo.search(q);
    }

    public Album getOrThrow(Long id) {
        return albumRepo.findById(id).orElseThrow();
    }
}
