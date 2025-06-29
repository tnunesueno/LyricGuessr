package com.example.lyricguessr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        Song.getTop100();
    }


    public static void main(String[] args) {
        launch();
    }

    public void stop() throws Exception {
    }
}