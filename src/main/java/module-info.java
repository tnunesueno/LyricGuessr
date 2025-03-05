module com.example.lyricguessr {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;


    opens com.example.lyricguessr to javafx.fxml;
    exports com.example.lyricguessr;
}