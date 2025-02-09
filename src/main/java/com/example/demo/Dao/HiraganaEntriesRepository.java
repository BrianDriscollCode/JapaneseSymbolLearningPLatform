package com.example.demo.Dao;

import com.example.demo.Entity.HiraganaEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiraganaEntriesRepository extends JpaRepository<HiraganaEntry, Long> {
}
