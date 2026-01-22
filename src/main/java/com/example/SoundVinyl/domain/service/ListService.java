package com.example.SoundVinyl.domain.service;

import com.example.SoundVinyl.domain.model.AlbumList;
import com.example.SoundVinyl.domain.model.AlbumListItem;
import com.example.SoundVinyl.domain.repository.AlbumListRepository;
import com.example.SoundVinyl.domain.repository.AlbumRepository;
import com.example.SoundVinyl.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListService {
    private final AlbumListRepository listRepo;
    private final AlbumRepository albumRepo;
    private final UserRepository userRepo;

    @Transactional
    public AlbumList create(Long ownerId, String name, String description, boolean isPublic) {
        var owner = userRepo.findById(ownerId).orElseThrow();
        var list = AlbumList.builder().owner(owner).name(name).description(description).isPublic(isPublic).build();

        return listRepo.save(list);
    }

    @Transactional
    public AlbumList addItem(Long listId, Long albumId, Integer position, String note) {
        var list = listRepo.findById(listId).orElseThrow();
        var album = albumRepo.findById(albumId).orElseThrow();

        var item = AlbumListItem.builder()
                .list(list).album(album)
                .position(position == null ? list.getItems().size() + 1 : position)
                .note(note).build();

        list.getItems().add(item);
        return listRepo.save(list);
    }
}
