package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.domain.model.ListeningEntry;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.ListeningEntryRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListeningService {

    private final ListeningEntryRepository listeningRepo;
    private final AlbumRepository albumRepo;
    private final UserRepository userRepo;

    public ListeningEntry log(Long userId, Long albumId, Double rating) {
        var user = userRepo.findById(userId).orElseThrow();
        var album = albumRepo.findById(albumId).orElseThrow();

        return listeningRepo.save(ListeningEntry.builder().user(user).album(album).rating(rating).build());
    }
}
