package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.AlbumList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumListRepository extends JpaRepository<AlbumList, Long> {
    List<AlbumList> findByOwnerIdOrderByCreatedAtDesc(Long ownerId);
}
