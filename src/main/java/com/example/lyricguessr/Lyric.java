package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lyric {
    @JsonProperty("lyrics")
    String lyrics;

    Song song;

    public ArrayList<String> lyricArray = new ArrayList<String>();

    public String getLyrics() { return lyrics; }

    public void setLyrics(String lyrics) { this.lyrics = lyrics; }

    public ArrayList<String> getLyricArray() {
        return lyricArray;
    }

    public void setLyricArray(ArrayList<String> lyricArray) {
        this.lyricArray = lyricArray;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public ArrayList<String> convertUsingAsList(String[] arr) {
        ArrayList<String> al = new ArrayList<>(Arrays.asList(arr));
        System.out.println(al);
        return al;
    }

    void fillArray(){
        System.out.println("Fill array called");
        String bigLyrics = this.getLyrics();

        String[] splitLines = bigLyrics.split("\\r?\\n");

        ArrayList<String> results = new ArrayList<>();
        Collections.addAll(results, splitLines);

        System.out.println("Number of lines: " + results.size());

        System.out.println("Number of lines: " +results.size());
        System.out.println("First line: " +results.getFirst());
        this.setLyricArray(results);

    }
}
