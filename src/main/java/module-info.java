module com.example.escriturarapida {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.escriturarapida to javafx.fxml;
    exports com.example.escriturarapida;
    exports com.example.escriturarapida.controller;
    opens com.example.escriturarapida.controller to javafx.fxml;
}