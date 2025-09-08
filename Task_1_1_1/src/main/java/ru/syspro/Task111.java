package ru.syspro;

/**
 * Class that implements Heap Sort.
 *
 */
public class Task111 {

    /**
     * Main method that starts sorting.
     *
     * @param arr unsorted array of integers.
     */
    public static void sort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    /**
     * Helper function to maintain the property of max heap.
     *
     * @param arr The array where we want to maintain heap properties.
     * @param n Size of the heap.
     * @param i Index of element whose subtree needs heapification.
     */

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    /**
     * Utility function to swap two elements in an array.
     *
     * @param arr The array containing elements to swap.
     * @param i Index of first element.
     * @param j Index of second element.
     */

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
