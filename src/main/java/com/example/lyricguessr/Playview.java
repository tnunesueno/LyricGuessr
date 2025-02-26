package com.example.lyricguessr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Playview {
 @FXML
private Text lyricText;

@FXML
private Text incorrect;

@FXML
private Text streakText;

 @FXML
 private TextField input;

 public ArrayList<String> Array = new ArrayList<String>();
 int selectedLyricNum;

 Song song;
 int streak=0;

 @FXML
 private Button playAgain;
    @FXML
    private Button newSong;

 public void initialize() throws Exception {
     streakText.setText("Streak: "+ streak);
     beginGame();
 }

 public void check(){
String firstLyric = Array.get(selectedLyricNum+1);
// get rid of spaces
     firstLyric=firstLyric.replace(" ", "");
     firstLyric = firstLyric.replace("'", "").replace("’", "");
     firstLyric=firstLyric.replace(",", "");
     firstLyric=firstLyric.replace(".", "");
     firstLyric=firstLyric.replace("-", "");
     firstLyric=firstLyric.replace("?","");
     firstLyric=firstLyric.replaceAll("\\(.*?\\)", "").trim();
     firstLyric=firstLyric.toLowerCase();

String guess = input.getText();

     guess= guess.replace(" ","");
     guess = guess.replace("'", "").replace("’", "");
     guess=guess.replace(",", "");
     guess=guess.replace(".", "");
     guess=guess.replace("-", "");
     guess=guess.replace("?","");
     guess=guess.replaceAll("\\(.*?\\)", "").trim();
     guess=guess.toLowerCase();

if(firstLyric.equalsIgnoreCase(guess)){
    System.out.println(firstLyric);
    System.out.println(guess);
    System.out.println("guess is CORRECT");
    incorrect.setText("Correct!! YAAYYY!!");
    playAgain.setVisible(true);
    streak = streak +1;
    streakText.setText("Streak: "+ streak);
} else {
    incorrect.setText("HINT: The song is called "+ song.getSongName() + "\n"+ " Try again!");
    System.out.println("guess is INCORRECT");
    System.out.println(firstLyric);
    System.out.println(guess);
    input.requestFocus();
    input.setText("");
    streak=0;
    streakText.setText("Streak: "+ streak);
   }
 }

 public void playAgain() throws Exception {
     incorrect.setText("");
     input.setText("");
     input.requestFocus();
     beginGame();
 }

 public void beginGame() throws Exception{
     Array.clear();
     playAgain.setVisible(false);
     incorrect.setText("");
     // this isnt random, it does a new lyric from the same song sometimes
     Lyric selectedLyrics = Song.randomLyrics();
     song = selectedLyrics.getSong();
     System.out.println("Size from play initialize: " +selectedLyrics.lyricArray.size());

     // adds all lyrics from song to this class's own array that can be used in other methods
     // this array needs to be cleared on start so it doesnt have a bunch of nonsense
     for (int l = 0; l<selectedLyrics.getLyricArray().size(); l++) {
         if (!selectedLyrics.getLyricArray().get(l).isEmpty()) {
             Array.add(selectedLyrics.getLyricArray().get(l));
         }
     }

     Random random = new Random();
     int lyricNUm = random.nextInt(0,Array.size());
     lyricText.setText(Array.get(lyricNUm));
     selectedLyricNum=lyricNUm;
 }
// make this not make a new window
 public void back() throws IOException {
     System.out.println("back called");
     FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
     Scene scene = new Scene(fxmlLoader.load(), 300, 500);
     Stage playStage = (Stage)input.getScene().getWindow();
     playStage.setTitle("Hello!");
     playStage.setScene(scene);
     playStage.show();
 }
}
