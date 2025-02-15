package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HiraganaRow {
    private String rowName;
    private List<HiraganaCharacter> characters;

    public HiraganaRow(String rowName)
    {
        this.rowName = rowName;
        this.characters = new ArrayList<>();
    }

    public void addCharacter(String character, String romaji, String column)
    {
        characters.add(new HiraganaCharacter(romaji, character, column));
    }

    public String getRowName() { return this.rowName; }
    public List<HiraganaCharacter> getCharacters() { return this.characters; }
}
