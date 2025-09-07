package ru.syspro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Task_1_1_1Test {
    @Test
    void testEmptyArray() {
        int[] input = {};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{}, input);
    }

    @Test
    void testSingleElementArray() {
        int[] input = {5};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{5}, input);
    }

    @Test
    void testSortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, input);
    }

    @Test
    void testReverseSortedArray() {
        int[] input = {5, 4, 3, 2, 1};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, input);
    }

    @Test
    void testRandomOrderArray() {
        int[] input = {3, 1, 4, 1, 5, 9, 2, 6, 8};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 8, 9}, input);
    }

    @Test
    void testNegativeNumbers() {
        int[] input = {-3, -1, -4, -1, -5, -9, -2, -6, -8};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{-9, -8, -6, -5, -4, -3, -2, -1, -1}, input);
    }

    @Test
    void testMixedPositiveAndNegativeNumbers() {
        int[] input = {3, -1, 4, 1, -5, 9, 2, -6, 8};
        Task_1_1_1.sort(input);
        assertArrayEquals(new int[]{-6, -5, -1, 1, 2, 3, 4, 8, 9}, input);
    }
}
