package com.example.escriturarapida;

import com.example.escriturarapida.view.GameStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class that launches the "Escritura Rápida" JavaFX application.
 * <p>
 * This class serves as the entry point of the program and initializes the main window.
 *</p>
 *
 * @author Sara González
 * <br>Student Code: 2519548-3743
 * @version 1.0
 */

public class Main extends Application {

    /**
     * Launches the JavaFX application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the primary stage of the application.
     * <p>
     * This method is automatically called by JavaFX and is responsible for
     * creating and displaying the main game window.
     *</p>
     *
     * @param primaryStage The main stage provided by JavaFX.
     * @throws Exception If an error occurs during initialization.
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        new GameStage(primaryStage);
    }
}
