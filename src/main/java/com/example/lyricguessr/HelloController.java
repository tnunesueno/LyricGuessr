package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.Serial;
import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button button;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void DisplayWords() throws Exception {
        Random random = new Random();
        int songNum = random.nextInt(99);

        Lyric lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
        if (lyrics == null) {
            if(songNum<99){
                songNum = songNum + 1;
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }else {
                songNum = songNum - 1;
                lyrics = Song.getLyricsFromSong(Song.allSongs.get(songNum));
            }
        }
        System.out.println(lyrics.getLyrics());
    }
}