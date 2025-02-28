package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "EntryProgress")
public class EntryProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EntryProgressID")
    private Long entryProgressID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Column(name = "kanaType", nullable = false)
    private String kanaType;

    @Column(name = "romaji", nullable = false)
    private String romaji;

    @Column(name = "score", nullable = false)
    private Long score;

    @Column(name = "rowGroup", nullable = false)
    private String rowGroup;

    // Getters and Setters

    public Long getEntryProgressID() {
        return entryProgressID;
    }

    public void setEntryProgressID(Long entryProgressID) {
        this.entryProgressID = entryProgressID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKanaType() {
        return kanaType;
    }

    public void setKanaType(String kanaType) {
        this.kanaType = kanaType;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getRowGroup() {
        return rowGroup;
    }

    public void setRowGroup(String rowGroup) {
        this.rowGroup = rowGroup;
    }
}


