package org.Daaa;

public class MergeSort {
    private static final int CUTOFF = 16; // Small array cutoff for insertion sort

    public static void sort(int[] arr, int[] buffer, int left, int right, Metrics metrics) {
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }

        int mid = (left + right) / 2;

        metrics.enterRecursion();
        sort(arr, buffer, left, mid, metrics);
        metrics.exitRecursion();

        metrics.enterRecursion();
        sort(arr, buffer, mid + 1, right, metrics);
        metrics.exitRecursion();

        merge(arr, buffer, left, mid, right, metrics);
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Metrics metrics) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            metrics.incrementComparisons();
            if (buffer[i] <= buffer[j]) arr[k++] = buffer[i++];
            else arr[k++] = buffer[j++];
        }

        while (i <= mid) {
            arr[k++] = buffer[i++];
        }
        while (j <= right) {
            arr[k++] = buffer[j++];
        }

        metrics.incrementAllocations();
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            metrics.incrementComparisons();
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
