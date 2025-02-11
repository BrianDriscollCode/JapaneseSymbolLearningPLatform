package com.example.demo.Entity;

import com.example.demo.Entity.BaseClass.Entry;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "EntryProgress")
public class EntryProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntryProgressID")
    private Long entryProgressID;  // Lowercased first letter for Java naming convention

    @OneToMany(mappedBy = "entryProgress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entry> entries;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false) // Corrected the column name
    private User user; // Changed from "users" to "user" (singular)

    @Column(name = "multiplechoicescore", nullable = false)
    private Integer multipleChoiceScore;

    @Column(name = "shortanswerscore", nullable = false)
    private Integer shortAnswerScore;

    @Column(name = "entrytype", nullable = false)
    private String entryType;
}

