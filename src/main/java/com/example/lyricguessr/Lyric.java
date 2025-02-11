package com.example.lyricguessr;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Lyric {

    @JsonProperty("lyrics")
    String lyrics;

    public String getLyrics() { return lyrics; }

    public void setLyrics(String lyrics) { this.lyrics = lyrics; }



}
