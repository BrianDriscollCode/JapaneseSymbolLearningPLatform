package com.example.demo.Entity.BaseClass;

import com.example.demo.Entity.EntryProgress;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryID;

    @ManyToOne
    @JoinColumn(name = "EntryProgressID", nullable = false)
    private EntryProgress entryProgress;

    @Column(name = "symbol", nullable = false)
    private Character symbol;

    @Column(name = "romaji", nullable = false)
    private String romaji;

    @Column(name = "rowgroup", nullable = false)
    private String rowGroup;

    @Column(name = "dakuten", nullable = false)
    private boolean dakuten;

    @Column(name = "handakuten", nullable = false)
    private boolean handakuten;
}
