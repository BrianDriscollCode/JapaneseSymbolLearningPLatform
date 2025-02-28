package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.UUID;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Matches method getId()

    @Column(name = "uuid", unique = true, nullable = false)
    private UUID uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // Getters and Setters
    public Long getId() { return this.id; } // Correct method name

    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public UUID getUuid() { return this.uuid; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }
}
