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
 int guessNum=5;
 Lyric selectedLyric;

 @FXML
public Text guesses;

 @FXML
 private Button playAgain;
    @FXML
    private Button show;

    @FXML
    private Button giveUp;

 public void initialize() throws Exception {
     streakText.setText("Streak: "+ streak);
     guesses.setText(guessNum + " guesses remaining");
     beginGame();
 }

 public void check(){
     String firstLyric;
     if (selectedLyricNum!=Array.size()){
      firstLyric= Array.get(selectedLyricNum+1);} else{
        selectedLyricNum = selectedLyricNum - 3;
         firstLyric=Array.get(selectedLyricNum+1);
     }

// get rid of spaces
     firstLyric=firstLyric.replace(" ", "");
     firstLyric = firstLyric.replace("'", "").replace("’", "");
     firstLyric=firstLyric.replace(",", "");
     firstLyric=firstLyric.replace(".", "");
     firstLyric=firstLyric.replace("-", "");
     firstLyric=firstLyric.replace("?","");
     firstLyric=firstLyric.replaceAll("\\(.*?\\)", "").trim();
     firstLyric=firstLyric.replace("&", "and");
     firstLyric=firstLyric.replace(" ","");
     firstLyric=firstLyric.toLowerCase();

String guess = input.getText();

if (guess!=null) {

    guess = guess.replace(" ", "");
    guess = guess.replace("'", "").replace("’", "");
    guess = guess.replace(",", "");
    guess = guess.replace(".", "");
    guess = guess.replace("-", "");
    guess = guess.replace("?", "");
    guess = guess.replaceAll("\\(.*?\\)", "").trim();
    guess = guess.toLowerCase();

    if (firstLyric.equalsIgnoreCase(guess) || (guess.contains(firstLyric))) {
        System.out.println(firstLyric);
        System.out.println(guess);
        System.out.println("guess is CORRECT");
        incorrect.setText("Correct!! YAAYYY!!");
        playAgain.setVisible(true);
        streak = streak + 1;
        streakText.setText("Streak: " + streak);
    } else {
        int space = Array.get(selectedLyricNum+1).indexOf(" ");
        if (space!=-1){
        incorrect.setText("HINT: The first word is " + Array.get(selectedLyricNum+1).substring(0,space) + "\n" + " Try again!");}
        else{
            incorrect.setText("HINT: The first word is " + Array.get(selectedLyricNum+1) + "\n" + " Try again!");
        }
        System.out.println("guess is INCORRECT");
        System.out.println(firstLyric);
        System.out.println(guess);
        input.requestFocus();
        input.setText("");
    }
}
     guessNum = guessNum-1;
    guesses.setText(guessNum+ " guesses remaining");

    if (guessNum==0){
        giveUP();
    }
 }

 public void playAgain() throws Exception {
     incorrect.setText("");
     input.setText("");
     input.requestFocus();
     beginGame();
 }

 public void beginGame() throws Exception{
     guessNum=3;
     guesses.setText(guessNum +" guesses remaining");
     Array.clear();
     playAgain.setVisible(false);
     incorrect.setText("");
     // this isnt random, it does a new lyric from the same song sometimes
     selectedLyric = Song.randomLyrics();
     song = selectedLyric.getSong();
     System.out.println("Size from play initialize: " +selectedLyric.lyricArray.size());

     // adds all lyrics from song to this class's own array that can be used in other methods
     // this array needs to be cleared on start so it doesnt have a bunch of nonsense
     for (int l = 0; l<selectedLyric.getLyricArray().size(); l++) {
         if (!selectedLyric.getLyricArray().get(l).isEmpty()) {
             Array.add(selectedLyric.getLyricArray().get(l));
         }
     }

     Random random = new Random();
     selectedLyricNum = random.nextInt(0,Array.size()-5);
     lyricText.setText(Array.get(selectedLyricNum));
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

 public void giveUP(){
     streak = 0;
     streakText.setText("Streak: "+ streak);

     incorrect.setText("Next lyric: "+ Array.get(selectedLyricNum+1)+"\n"+ "Song: "+ selectedLyric.getSong().getSongName() + " by " +selectedLyric.getSong().getArtist());
     playAgain.setVisible(true);
 }

 public void showSong(){
     lyricText.setText(lyricText.getText()+ "\n"+ "Song: "+selectedLyric.getSong().getSongName() + " by " +selectedLyric.getSong().getArtist());
 }
}
