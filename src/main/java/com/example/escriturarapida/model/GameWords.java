package com.example.escriturarapida.model;

import java.util.Random;

/**
 * Model class that implements the IWords interface.
 * <p>
 * It manages three word banks with increasing difficulty levels.
 * <p>
 * It provides functionality to generate random words based on the current level
 * and ensures that words do not repeat consecutively.
 * <p>
 * It also validates the user input.
 * </p>
 *
 * @author Sara González
 * <br>Student Code: 2519548-3743
 * @version 1.0
 */
public class GameWords implements IWords {

    private Random randomGenerator;
    private String lastWord = "";

    /**
     * Array of easy words (levels 1–15).
     */
    private String[] easyWords = {"Azul", "Banano", "Perro", "Garabato", "Luna", "Coco", "Pescado",
            "Almendra", "Granja", "Reloj", "Libro", "Cerillo", "Filosofía",
            "Hipopótamo", "Teclado", "Cuchara", "Botella", "Planeta", "Guitarra",
            "Estrella", "Zapato", "Pelota", "Galleta", "Monalisa", "Reflejo",
            "Camino", "Bosque", "Espejo", "Palabra", "Ruido"};

    /**
     * Array of medium difficulty words (levels 16–30).
     */
    private String[] mediumWords = {"Videocasetera", "Ingeniería", "Ferrocarril", "Programación", "Inteligencia",
            "Arquitectura", "Sincronización", "Caleidoscopio", "Computadora", "Dinosaurio",
            "Escritorio", "Microscopio", "Diccionario", "Astronauta",
            "Chocolate", "Mariposa", "Estructura", "Algoritmo", "Framework", "Polimorfismo", "Instancia",
            "Herencia", "Abstracción", "Encapsulación", "Interface", "Variable"};

    /**
     * Array of hard words (levels 31–45).
     */
    private String[] hardWords = {"Ornitorrinco", "Paralelepípedo", "Electrodoméstico", "Esternocleidomastoideo",
            "Otorrinolaringólogo", "Desoxirribonucleico", "Arqueobacterias", "Ventrílocuo", "Inconstitucional",
            "Idiosincrasia", "Espectrograma", "Electrocardiograma", "Paleontología", "Antropología",
            "Espectroscopía", "Neurociencia", "Metamorfosis", "Contrarreloj", "Jurisprudencia",
            "Cinematografía", "Radiocomunicación", "Ininteligible", "Vicisitud", "Escaramuza", "Ovovivíparo"};

    /**
     * Constructor for GameWords. Initializes the random number generator.
     */
    public GameWords(){
        this.randomGenerator = new Random();
    }

    /**
     * Selects a random word from the appropriate bank based on the current level.
     * <p>
     * It ensures that the same word is not repeated consecutively.
     * </p>
     *
     * @param level The current level of the player.
     * @return A randomly selected word from the corresponding difficulty bank.
     */
    @Override
    public String generateNextWord(int level) {
        String[] selectBank;

        if (level <= 15) {
            selectBank = easyWords;
        } else if (level <= 30) {
            selectBank = mediumWords;
        } else {
            selectBank = hardWords;
        }

        int index = randomGenerator.nextInt(selectBank.length);
        String nextWord = selectBank[index];

        while (nextWord.equals(lastWord)) {
            index = randomGenerator.nextInt(selectBank.length);
            nextWord = selectBank[index];
        }
        lastWord = nextWord;
        return nextWord;
    }

    /**
     * Validates if the user input matches the current word.
     * </p>
     *
     * @param currentWord The target word to be matched.
     * @param userInput The text typed by the player.
     * @return true if both strings are identical, false otherwise.
     */
    @Override
    public boolean checkWord(String currentWord, String userInput) {
        return currentWord.equals(userInput);
    }
}



