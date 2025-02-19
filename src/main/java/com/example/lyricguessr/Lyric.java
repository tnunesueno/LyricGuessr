package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Lyric {
    @JsonProperty("lyrics")
    String lyrics;

    public ArrayList<String> lyricArray = new ArrayList<>();

    public String getLyrics() { return lyrics; }

    public void setLyrics(String lyrics) { this.lyrics = lyrics; }


    void fillArray(){
        System.out.println("Fill array called");
        String bigLyrics = this.getLyrics();
        String[] results = bigLyrics.split("//n");
        for ( int i = 0; i < this.lyricArray.size(); i++) {
            this.lyricArray.add(results[i]);
            System.out.println("New line added: " + this.lyricArray.get(i));
        }

    }
}
