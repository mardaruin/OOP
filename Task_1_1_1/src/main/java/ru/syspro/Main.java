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
        int[] exampleArray = {12, 11, 13, 5, 6, 7};
        System.out.println("Unsorted array: " + Arrays.toString(exampleArray));

        Task111.sort(exampleArray);

        System.out.println("\nSorted array: " + Arrays.toString(exampleArray));
    }
}