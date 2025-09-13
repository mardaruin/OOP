package ru.syspro;

import java.util.Arrays;

/**
 * Entry point for running the program directly from the command-line.
 */

public class Main {

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame();
        game.play();
    }
}