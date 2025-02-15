package com.example.demo.model;

public class HiraganaCharacter {
    private String character;
    private String romaji;
    private String column;

    public HiraganaCharacter(String character, String romaji, String column) {
        this.character = character;
        this.romaji = romaji;
        this.column = column;
    }

    public String getCharacter() {
        return character;
    }

    public String getRomaji() {
        return romaji;
    }

    public String getColumn() {
        return column;
    }
}

