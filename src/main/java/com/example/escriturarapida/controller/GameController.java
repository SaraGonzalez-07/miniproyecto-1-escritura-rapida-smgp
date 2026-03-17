package com.example.escriturarapida.controller;

import com.example.escriturarapida.model.IWords;
import com.example.escriturarapida.model.GameWords;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * Controller class that manages the interaction between the GUI (FXML) and the game logic (Model).
 * <p>
 * It handles user interactions, updates the interface in real time,
 * and controls the game flow such as levels, timer, and validation.
 * <p>
 * This class follows the MVC pattern to ensure modularity.
 * </p>
 *
 * @author Sara González
 * <br>Student Code: 2519548-3743
 * @version 1.0
 */
public class GameController {

    // FXML bindings (UI components)
    @FXML private Label levelLabel;
    @FXML private Label timerLabel;
    @FXML private Label wordDisplay;
    @FXML private Label feedbackLabel;
    @FXML private TextField inputField;
    @FXML private Button actionButton;

    // Game control attributes
    private IWords gameWords;
    private Timeline timeline;
    private int time;
    private int secondsLeft;
    private int currentLevel;
    private String currentWord;

    /**
     * Initializes the controller after the FXML is loaded.
     * <p>
     * Configures the initial game state and sets event handlers for the button and text field.
     * </p>
     */
    public void initialize() {
        gameWords = new GameWords();
        inputField.setDisable(true);

        // Button action ("JUGAR" / "VOLVER A JUGAR")
        actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                feedbackLabel.setText("");
                startGame();
            }
        });

        // ENTER key in text field
        inputField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                processWord();
            }
        });
    }

    /**
     * Starts a new game.
     * <p>
     * Resets all variables and prepares the UI for gameplay.
     * </p>
     */
    private void startGame() {
        currentLevel = 1;
        time = 20;
        secondsLeft = time;
        inputField.setDisable(false);
        inputField.clear();
        inputField.requestFocus();
        actionButton.setVisible(false);

        loadNewLevel();
    }

    /**
     * Prepares the interface for the next word.
     * <p>
     * Generates a new word from the model, resets the timer, and starts the countdown.
     * </p>
     */
    private void loadNewLevel() {

        if (timeline != null) timeline.stop();

        secondsLeft = time;
        updateTimerLabel();

        currentWord = gameWords.generateNextWord(currentLevel);
        wordDisplay.setText(currentWord);
        levelLabel.setText(String.format("%02d", currentLevel));

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondsLeft--;
                updateTimerLabel();

                if (secondsLeft <= 0) {
                    endGame("¡SE ACABÓ EL TIEMPO!");
                }
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Processes user input and validates it against the current word.
     * <p>
     * Increases difficulty by reducing time every 5 levels and checks for victory.
     * </p>
     */
    private void processWord() {
        String userInput = inputField.getText();

        if (gameWords.checkWord(currentWord, userInput)) {
            currentLevel++;

            if (currentLevel %5==1 && time >2) {
                time -=2;
            }

            inputField.clear();
            feedbackLabel.setText("¡CORRECTO!");

            if (currentLevel>45){
                endGame("¡FELICIDADES! HAS COMPLETADO EL JUEGO");
            } else {
                loadNewLevel();
            }
        } else {

            endGame("¡INCORRECTO!");
        }
    }

    /**
     * Terminates the game session and displays a performance summary.
     * <p>
     * It adjusts the displayed level to ensure it does not exceed the maximum of 45.
     * <p>
     * Provides specific feedback if the user made a mistake or ran out of time.
     * </p>
     *
     * @param message The cause of the game ending (Correct, Incorrect, or Timeout).
     */
    private void endGame(String message) {
        if (timeline != null) timeline.stop();

        inputField.setDisable(true);
        actionButton.setVisible(true);
        actionButton.setText("VOLVER A JUGAR");

        int levelToShow = currentLevel;

        if (levelToShow > 45) {
            levelToShow = 45;
        }

        String summary = message + "\n" ;

        if (message.equals("¡INCORRECTO!")){
            summary += "La palabra correcta era: " + currentWord + "\n";
        }

        summary += "Nivel alcanzado: " + String.format("%02d", levelToShow) + "\n" +
                "Tiempo restante: " + String.format("%02d", secondsLeft) + "s";

        feedbackLabel.setText(summary);
        wordDisplay.setText("FIN DEL JUEGO");
    }

    /**
     * Updates the timer label in format "00:XX".
     */
    private void updateTimerLabel() {
        timerLabel.setText("00:" + String.format("%02d", secondsLeft));
    }
}