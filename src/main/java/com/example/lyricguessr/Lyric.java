package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lyric {
    @JsonProperty("lyrics")
    String lyrics;

    public String getLyrics() { return lyrics; }

    public void setLyrics(String lyrics) { this.lyrics = lyrics; }

}
