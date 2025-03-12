package com.example.demo.Dao;

import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntryProgressRepository extends JpaRepository<EntryProgress, Long> {
    List<EntryProgress> findByUser(User user);
    Optional<EntryProgress> findByUserAndRomaji(User user, String romaji);
    Optional<EntryProgress> findByUserAndRomajiAndKanaType(User user, String romaji, String kanaType);


}
