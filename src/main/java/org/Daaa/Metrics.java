package org.Daaa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Metrics {
    private int comparisons = 0;
    private int allocations = 0;
    private int recursionDepth = 0;
    private int maxRecursionDepth = 0;

    // Increment comparisons
    public void incrementComparisons() {
        comparisons++;
    }

    // Increment allocations
    public void incrementAllocations() {
        allocations++;
    }

    // Track recursion depth
    public void enterRecursion() {
        recursionDepth++;
        if (recursionDepth > maxRecursionDepth) {
            maxRecursionDepth = recursionDepth;
        }
    }

    public void exitRecursion() {
        recursionDepth--;
    }

    // Write metrics to CSV
    public void writeMetricsToCSV(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(comparisons + "," + allocations + "," + maxRecursionDepth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for metrics
    public int getComparisons() {
        return comparisons;
    }

    public int getAllocations() {
        return allocations;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }
}