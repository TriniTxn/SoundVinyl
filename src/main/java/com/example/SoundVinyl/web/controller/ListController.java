package com.example.SoundVinyl.web.controller;

import com.example.SoundVinyl.app.dto.ListCreateRequest;
import com.example.SoundVinyl.app.dto.ListItemRequest;
import com.example.SoundVinyl.domain.model.AlbumList;
import com.example.SoundVinyl.domain.repository.AlbumListRepository;
import com.example.SoundVinyl.domain.service.ListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lists")
public class ListController {

    private static final Long CURRENT_USER_ID = 1L;

    private final ListService listService;
    private final AlbumListRepository listRepo;

    @PostMapping
    public AlbumList create(@Valid @RequestBody ListCreateRequest request) {
        return listService.create(CURRENT_USER_ID, request.name(), request.description(), request.isPublic());
    }

    @GetMapping
    public List<AlbumList> myLists() {
        return listRepo.findByOwnerIdOrderByCreatedAtDesc(CURRENT_USER_ID);
    }

    @PostMapping("/{id}/items")
    public AlbumList addItem(@PathVariable Long id, @Valid @RequestBody ListItemRequest request) {
        return listService.addItem(id, request.albumId(), request.position(), request.note());
    }
}
