package com.example.SoundVinyl.domain.repository;

import com.example.SoundVinyl.domain.model.ListeningEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningEntryRepository extends JpaRepository<ListeningEntry, Long> {
    List<ListeningEntry> findByUserIdOrderByListenedAtDesc(Long userId);
}
