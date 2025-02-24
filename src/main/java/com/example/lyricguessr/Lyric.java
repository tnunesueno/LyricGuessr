package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Lyric {
    @JsonProperty("lyrics")
    String lyrics;

    public ArrayList<String> lyricArray = new ArrayList<String>();

    public String getLyrics() { return lyrics; }

    public void setLyrics(String lyrics) { this.lyrics = lyrics; }

    public ArrayList<String> getLyricArray() {
        return lyricArray;
    }

    public void setLyricArray(ArrayList<String> lyricArray) {
        this.lyricArray = lyricArray;
    }

    public ArrayList<String> convertUsingAsList(String[] arr) {
        ArrayList<String> al = new ArrayList<>(Arrays.asList(arr));
        //System.out.println(al);
        return al;
    }

    void fillArray(){
        System.out.println("Fill array called");
        String bigLyrics = this.getLyrics();

        String[] results = bigLyrics.split("\n");
        System.out.println("array Number of lines: "+results.length);
        System.out.println("array First line: "+results[0]);

        ArrayList<String> resultsList = convertUsingAsList(results);
        System.out.println("Number of lines: " +resultsList.size());
        System.out.println("First line: " +resultsList.getFirst());
    }
}
