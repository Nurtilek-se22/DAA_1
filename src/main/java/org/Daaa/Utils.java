package org.Daaa;

import java.util.Random;

public class Utils {

    // Partition function for QuickSort and Deterministic Select
    public static int partition(int[] arr, int left, int right, int pivotIndex, Metrics metrics) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right, metrics);  // Move pivot to end
        metrics.incrementAllocations();

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex, metrics);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right, metrics);  // Move pivot to its final place
        return storeIndex;
    }

    // Swap function to swap two elements in the array
    public static void swap(int[] arr, int i, int j, Metrics metrics) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        metrics.incrementAllocations();  // Track the swap as an allocation
    }

    // Shuffle function for randomizing the array
    public static void shuffle(int[] arr, Metrics metrics) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);  // Random index from 0 to i
            swap(arr, i, j, metrics);
        }
    }

    // Guard function to check for out-of-bounds or invalid input
    public static void guardArrayBounds(int[] arr, int left, int right) {
        if (left < 0 || right >= arr.length || left > right) {
            throw new IllegalArgumentException("Array bounds are out of range or invalid.");
        }
    }

    // Guard for checking if the index is valid for the array
    public static void guardIndex(int[] arr, int index) {
        if (index < 0 || index >= arr.length) {
            throw new IllegalArgumentException("Index is out of bounds for the array.");
        }
    }

    // Guard for checking if the array is null or empty
    public static void guardNonEmptyArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
    }
}
