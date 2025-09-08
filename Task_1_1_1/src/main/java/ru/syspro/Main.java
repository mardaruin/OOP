package ru.syspro;

/**
 * Entry point for running the program directly from the command-line.
 */

public class Main {

    /**
     * Main entry point for launching the application.
     */
    public static void main(String[] args) {
        int[] exampleArray = {12, 11, 13, 5, 6, 7};
        System.out.println("Unsorted array:");
        printArray(exampleArray);

        Task111.sort(exampleArray);

        System.out.println("\nSorted array:");
        printArray(exampleArray);
    }

    /**
     * Helper function to print contents of an array.
     *
     * @param arr Array to be printed.
     */
    private static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}