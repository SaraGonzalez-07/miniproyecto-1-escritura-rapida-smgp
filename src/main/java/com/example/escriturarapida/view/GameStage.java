package com.example.escriturarapida.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * View class responsible for managing the game window (Stage).
 * <p>
 * It loads the FXML layout, sets up the Scene, and displays the graphical interface.
 *</p>
 *
 * @author Sara Gonzalez
 * <br>Student Code: 2519548-3743
 * @version 1.0
 */

public class GameStage {

    /**
     * Constructs the GameStage and displays the main window.
     * <p>
     * This constructor loads the GameView.fxml file, creates a scene, sets window properties
     * and assigns it to the provided stage.
     * </p>
     *
     * @param stage The primary stage where the scene will be displayed.
     * @throws IOException If the FXML file cannot be found or loaded.
     */
    public GameStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/escriturarapida/GameView.fxml"));
        Scene scene = new Scene(loader.load(), 600, 600);

        stage.setTitle("Escritura Rápida");
        stage.setScene(scene);
        stage.show();
    }
}
