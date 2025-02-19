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
     HelloController.DisplayWords();
     Lyric selectedLyrics = Song.randomLyrics();
     selectedLyrics.fillArray();

    //lyricText.setText(selectedLyrics.lyricArray.get(5));
 }
}
