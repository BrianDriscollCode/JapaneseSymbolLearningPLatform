package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public Long getUserID() { return this.userID; }

    public void setUUID(UUID uuid) { this.uuid = uuid; }
    public UUID getUUID() { return this.uuid; }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return this.email; }
}
