module com.example.ejerciciohebra {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ejerciciohebra to javafx.fxml;
    exports com.example.ejerciciohebra;
}