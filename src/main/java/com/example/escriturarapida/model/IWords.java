package com.example.escriturarapida.model;

/**
 * Interface that defines the contract for word management in the game.
 * <p>
 * Classes that implement this interface must provide logic to generate
 * words based on a difficulty level and to validate user input.
 * </p>
 *
 * @author Sara González
 * <br>Student Code: 2519548-3743
 * @version 1.0
 */
public interface IWords {

    /**
     * Selects and returns a word based on the current game level.
     * <p>
     * The difficulty of the word should increase as the level progresses.
     * </p>
     *
     * @param level The current level reached by the player.
     * @return A String representing the next word to be typed.
     */
    String generateNextWord(int level);

    /**
     * Checks if the user's input matches the current word.
     * <p>
     * This method compares the expected word with the user input
     * and returns whether they are equal.
     * </p>
     *
     * @param currentWord The expected word.
     * @param userInput The word entered by the user.
     * @return true if the words match, false otherwise.
     */
    boolean checkWord(String currentWord, String userInput);
}
