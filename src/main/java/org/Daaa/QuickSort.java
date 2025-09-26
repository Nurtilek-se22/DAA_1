package org.Daaa;

public class QuickSort {
    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, metrics);
    }

    private static void quickSort(int[] arr, int left, int right, Metrics metrics) {
        if (left < right) {
            metrics.enterRecursion();
            int pivotIndex = partition(arr, left, right, metrics);
            quickSort(arr, left, pivotIndex - 1, metrics);
            quickSort(arr, pivotIndex + 1, right, metrics);
            metrics.exitRecursion();
        }
    }

    private static int partition(int[] arr, int left, int right, Metrics metrics) {
        int pivotIndex = left + (int)(Math.random() * (right - left + 1));  // Random pivot
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        metrics.incrementAllocations();

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            metrics.incrementComparisons();
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
