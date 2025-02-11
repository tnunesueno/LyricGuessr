package com.example.lyricguessr;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {

        welcomeText.setText("Welcome to JavaFX Application!");

    }
}