package org.Daaa;

import java.util.Arrays;

public class DeterministicSelect {

    // Public method to find the k-th smallest element in the array
    public static int select(int[] arr, int k, Metrics metrics) {
        // Guard for empty array
        Utils.guardNonEmptyArray(arr);
        return select(arr, 0, arr.length - 1, k, metrics);
    }

    // Helper method to perform the selection
    private static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        // Base case: if the array has only one element
        if (left == right) {
            return arr[left];
        }

        // Find the pivot using Median of Medians
        int pivotIndex = medianOfMedians(arr, left, right, metrics);

        // Partition the array and get the pivot position
        pivotIndex = Utils.partition(arr, left, right, pivotIndex, metrics);

        // Recursively select the side that contains the k-th smallest element
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return select(arr, left, pivotIndex - 1, k, metrics);
        } else {
            return select(arr, pivotIndex + 1, right, k, metrics);
        }
    }

    // Find the Median of Medians
    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        int numMedians = (n + 4) / 5;  // Number of groups of 5
        int[] medians = new int[numMedians];

        // Group the array into sub-arrays of 5 elements and find medians
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];  // Median of each group
        }

        // Recursively find the median of the medians
        return select(medians, 0, medians.length - 1, medians.length / 2, metrics);
    }
}
