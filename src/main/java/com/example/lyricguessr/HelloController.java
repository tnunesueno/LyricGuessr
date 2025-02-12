package com.example.lyricguessr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button RL;

    public void onHelloButtonClick() throws Exception {

        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void DisplayWords(){
        Random random = new Random();
        int songNum = random.nextInt(99);

        Lyric lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
        return lyrics.getLyrics();
    }

}