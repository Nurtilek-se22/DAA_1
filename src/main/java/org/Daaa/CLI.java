package org.Daaa;

import java.io.*;
import java.util.*;

public class CLI {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java CLI <algorithm> <args>");
            System.exit(1);
        }

        String algorithm = args[0];
        Metrics metrics = new Metrics();

        // Parse the input based on the algorithm
        switch (algorithm) {
            case "mergesort":
                runMergeSort(args, metrics);
                break;
            case "quicksort":
                runQuickSort(args, metrics);
                break;
            case "select":
                runDeterministicSelect(args, metrics);
                break;
            case "closestpair":
                runClosestPair(args, metrics);
                break;
            default:
                System.out.println("Unknown algorithm: " + algorithm);
                System.exit(1);
        }

        // Emit metrics to CSV
        metrics.writeMetricsToCSV("metrics.csv");
    }

    private static void runMergeSort(String[] args, Metrics metrics) {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Sample data, can be modified to parse from args
        int[] buffer = new int[arr.length];
        MergeSort.sort(arr, buffer, 0, arr.length - 1, metrics);
        System.out.println("MergeSort completed.");
    }

    private static void runQuickSort(String[] args, Metrics metrics) {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Sample data
        QuickSort.sort(arr, metrics);
        System.out.println("QuickSort completed.");
    }

    private static void runDeterministicSelect(String[] args, Metrics metrics) {
        int[] arr = {5, 2, 9, 1, 5, 6};  // Sample data
        int k = 2;  // Example k value (3rd smallest element)
        int result = DeterministicSelect.select(arr, k, metrics);
        System.out.println("Deterministic Select result: " + result);
    }

    private static void runClosestPair(String[] args, Metrics metrics) {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(3, 3),
                new ClosestPair.Point(5, 5)
        };  // Sample points
        double result = ClosestPair.closestPair(points, metrics);
        System.out.println("Closest Pair result: " + result);
    }
}