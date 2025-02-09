package com.example.demo.Entity;

import com.example.demo.Entity.BaseClass.Entry;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UserProgress")
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @OneToMany(mappedBy = "userProgress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entry> entries;

    @Column(name = "multiplechoicescore", nullable = false)
    private Integer multipleChoiceScore;

    @Column(name = "shortanswerscore", nullable = false)
    private Integer shortAnswerScore;

    @Column(name = "entrytype", nullable = false)
    private String entryType;
}
