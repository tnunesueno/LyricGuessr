package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public static void DisplayWords() throws Exception {
        Song.getTop100();
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

    public void play() throws Exception{
        System.out.println("play called");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("playview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        Stage playStage = (Stage)button.getScene().getWindow();
        playStage.setTitle("Hello!");
        playStage.setScene(scene);
        playStage.show();
    }
}