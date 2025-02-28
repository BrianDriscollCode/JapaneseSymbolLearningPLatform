package com.example.demo.Dto;

import com.example.demo.model.Entry;

import java.util.List;
import java.util.UUID;

import java.util.List;
import java.util.UUID;

public class EntryProgressRequestDTO {

    private List<Entry> entries;
    private UUID uuid;

    // Getter for entries
    public List<Entry> getEntries() {
        return entries;
    }

    // Setter for entries
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    // Getter for uuid
    public UUID getUuid() {
        return uuid;
    }

    // Setter for uuid
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

