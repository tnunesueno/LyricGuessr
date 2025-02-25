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
    private Text incorrect;


 @FXML
 private TextField input;

 public ArrayList<String> Array = new ArrayList<String>();
 public String selectedLyric;
 int selectedLyricNum;

 Song song;

 public void initialize() throws Exception {
     Lyric selectedLyrics = Song.randomLyrics();
     song = selectedLyrics.getSong();
     System.out.println("Size from play initialize: " +selectedLyrics.lyricArray.size());

     // adds all lyrics from song to this class's own array that can be used in other methods

     for (int l = 0; l<selectedLyrics.getLyricArray().size(); l++) {
         if (!selectedLyrics.getLyricArray().get(l).isEmpty()) {
             Array.add(selectedLyrics.getLyricArray().get(l));
         }
     }
     for (int i = 0; i<Array.size(); i++){
         System.out.println("line from array: "+ Array.get(i));
     }

     Random random = new Random();
     int lyricNUm = random.nextInt(0,Array.size());
     lyricText.setText(Array.get(lyricNUm));
     selectedLyricNum=lyricNUm;
 }

 public void check(){
     // FIND A WAY TO GET RID OF STUFF IN PARENS
String firstLyric = Array.get(selectedLyricNum+1);
// get rid of spaces
firstLyric.replace(" ", "");
String guess = input.getText();
guess.replace(" ","");

if(firstLyric.equalsIgnoreCase(guess)){
    System.out.println(firstLyric);
    System.out.println(guess);
    System.out.println("guess is correct");
} else {
    incorrect.setText("song name: "+ song.getSongName());
    System.out.println("guess is INCORRECT");

}
 }
}
