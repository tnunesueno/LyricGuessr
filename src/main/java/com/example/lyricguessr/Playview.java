package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;

public class Playview {
 @FXML
private Text lyricText;

 @FXML
 private TextField input;

 public ArrayList<String> Array = new ArrayList<String>();

 public void initialize() throws Exception {
     Lyric selectedLyrics = Song.randomLyrics();
     System.out.println("Size from play initialize: " +selectedLyrics.lyricArray.size());

     Random random = new Random();
     int lyricNUm = random.nextInt(3,selectedLyrics.lyricArray.size()-2);

     lyricText.setText(selectedLyrics.lyricArray.get(lyricNUm));

     // adds all lyrics from song to this class's own array that can be used in other methods
     for (int l = 0; l<selectedLyrics.getLyricArray().size(); l++){
         if (!selectedLyrics.getLyricArray().get(l).isEmpty()){
         Array.add(selectedLyrics.getLyricArray().get(l));
         System.out.println("added line: "+ Array.get(l));} else {
             System.out.println("line is empty, moving on");
         }
     }
 }
}
