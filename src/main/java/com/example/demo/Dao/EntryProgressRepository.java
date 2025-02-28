package com.example.demo.Dao;

import com.example.demo.Entity.EntryProgress;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryProgressRepository extends JpaRepository<EntryProgress, Long> {
    List<EntryProgress> findByUserId(User user);
}
