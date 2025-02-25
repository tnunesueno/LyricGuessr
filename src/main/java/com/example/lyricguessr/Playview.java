package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Random;

public class Playview {
 @FXML
private Text lyricText;

 @FXML
 private TextField input;

 public void initialize() throws Exception {
     Lyric selectedLyrics = Song.randomLyrics();
     System.out.println("Size from play initialize: " +selectedLyrics.lyricArray.size());

     Random random = new Random();
     int lyricNUm = random.nextInt(3,selectedLyrics.lyricArray.size()-2);

     lyricText.setText(selectedLyrics.lyricArray.get(lyricNUm));

     }
 }
