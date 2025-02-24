package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Playview {
 @FXML
private Text lyricText;

 @FXML
 private TextField input;

 public void initialize() throws Exception {
     Lyric selectedLyrics = Song.randomLyrics();
    selectedLyrics.fillArray();

     String displayLyric = selectedLyrics.getLyricArray().get(10);
     lyricText.setText(displayLyric);
 }
}
