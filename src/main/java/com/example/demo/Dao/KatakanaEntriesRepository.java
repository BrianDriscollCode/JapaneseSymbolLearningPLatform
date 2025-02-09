package com.example.demo.Dao;

import com.example.demo.Entity.KatakanaEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KatakanaEntriesRepository extends JpaRepository<KatakanaEntry, Long> {
}
