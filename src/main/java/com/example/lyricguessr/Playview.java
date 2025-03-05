package com.example.lyricguessr;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
 public StackPane stackPane;
 public ImageView imageView;
 public VBox vBox;

 public ArrayList<String> Array = new ArrayList<String>();
 int selectedLyricNum;

 Song song;
 int streak=0;
 int guessNum=5;
 Lyric selectedLyric;
 String selectedLine;
 int wordNum=0;

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

 public void check() throws Exception {
     String nextLyric;
     // check that the selected line is not the last line of the song
     if (selectedLyricNum!=Array.size()){
      nextLyric = Array.get(selectedLyricNum+1);} else{
        selectedLyricNum = selectedLyricNum - 3;
         nextLyric =Array.get(selectedLyricNum+1);
     }
     nextLyric = nextLyric.replace(" "," ");
     nextLyric = nextLyric.replaceAll(" ", " ").trim();

     String[] splitWords = nextLyric.split(" ");
     ArrayList<String> wordArray = new ArrayList<>();
     Collections.addAll(wordArray, splitWords);

// get rid of spaces
     nextLyric = nextLyric.replace(" ", "");
     nextLyric = nextLyric.replace("'", "").replace("’", "");
     nextLyric = nextLyric.replace(",", "");
     nextLyric = nextLyric.replace(".", "");
     nextLyric = nextLyric.replace("-", "");
     nextLyric = nextLyric.replace("?","");
     nextLyric = nextLyric.replaceAll("\\(.*?\\)", "").trim();
     nextLyric = nextLyric.replace("&", "and");

     nextLyric = nextLyric.replace("\"","");
     nextLyric = nextLyric.toLowerCase();
     nextLyric = nextLyric.replace("ú","u");
     nextLyric = nextLyric.replace("ñ","n");
     nextLyric = nextLyric.replace("é","e");
     nextLyric = nextLyric.replace("á","a");
     nextLyric = nextLyric.replace("í","i");
     nextLyric = nextLyric.replace("ó","o");

String guess = input.getText();
if (guess!=null) {

    guess = guess.replace(" ", "");
    guess = guess.replace("'", "").replace("’", "");
    guess = guess.replace(",", "");
    guess = guess.replace(".", "");
    guess = guess.replace("-", "");
    guess = guess.replace("?", "");
    guess = guess.replaceAll("\\(.*?\\)", "").trim();
    guess = guess.replaceAll(" ", "").trim();
    guess = guess.toLowerCase();
    guess = guess.replace("ú","u");
    guess=guess.replace("ñ","n");
    guess=guess.replace("é","e");
    guess=guess.replace("á","a");
    guess=guess.replace("í","i");
    guess=guess.replace("ó","o");
    //r t

    if (nextLyric.equalsIgnoreCase(guess) || (guess.contains(nextLyric))) {
        System.out.println(nextLyric);
        System.out.println(guess);
        System.out.println("guess is CORRECT");
        incorrect.setText("Correct!! YAAYYY!!");
        playAgain.setVisible(true);
        streak = streak + 1;
        streakText.setText("Streak: " + streak);
        showImage();
    } else {
        if (wordNum==1){
        incorrect.setText("HINT: The line starts with " + wordArray.get(wordNum-1) + " "+wordArray.get(wordNum)+ "\n" + " Try again!");}
        // pretty sure it would only be 0 or 1 since u die at 3 guesses aka index 2 - keep this around in case u want more guesses
        else if (wordNum==2){
            incorrect.setText("HINT: The line starts with " + wordArray.get(wordNum-2) +" "+wordArray.get(wordNum-1)+ " "+ wordArray.get(wordNum)+ "\n" + " Try again!");
        } else {
            incorrect.setText("HINT: The line starts with " + wordArray.get(wordNum) +"\n" + " Try again!");
        }
        System.out.println("guess is INCORRECT");
        System.out.println(nextLyric);
        System.out.println(guess);
        input.requestFocus();
        input.setText("");
        guessNum = guessNum-1;
        guesses.setText(guessNum+ " guesses remaining");
        if (guessNum==0){
            giveUP();
        }
        wordNum=wordNum+1;
    }
}
 }

 public void playAgain() throws Exception {
     incorrect.setText("");
     input.setText("");
     input.requestFocus();
     beginGame();
 }

 public void beginGame() throws Exception{
    // vBox.setSpacing(50);
     input.setEditable(true);
     show.setDisable(false);
     guessNum=3;
     wordNum=0;
     stackPane.getChildren().clear();
     stackPane.getChildren().add(imageView);
     stackPane.getChildren().add(input);
     imageView.setImage(null);
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

 public void giveUP() throws Exception {
     streak = 0;
     streakText.setText("Streak: "+ streak);

     incorrect.setText("Next lyric: "+ Array.get(selectedLyricNum+1)+"\n"+ "Song: "+ selectedLyric.getSong().getSongName() + " by " +selectedLyric.getSong().getArtist());
     playAgain.setVisible(true);
     input.setEditable(false);
     Song.getAlbumArt(selectedLyric.getSong());
     showImage();
 }

 public void showSong(){
     lyricText.setText(lyricText.getText()+ "\n"+ "Song: "+selectedLyric.getSong().getSongName() + " by " +selectedLyric.getSong().getArtist());
     show.setDisable(true);
 }

 public void showImage() throws Exception {

     URL artURL = Song.getAlbumArt(selectedLyric.getSong());
     stackPane.getChildren().remove(input);
     Image albumArt = new Image(String.valueOf(artURL));
     vBox.setSpacing(20);
     vBox.setAlignment(Pos.CENTER);
     imageView.setImage(albumArt);
     imageView.setFitHeight(150);
     stackPane.setPrefHeight(150);
 }
}

// GOOGLE API KEY AIzaSyBixnwAc3dICuzHev9klwJgzLn0Vf5VkMQ
