package org.Daaa;

public class ClosestPair {
    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Closest Pair method (entry point)
    public static double closestPair(Point[] points, Metrics metrics) {
        // Sort points by x-coordinate
        Point[] sortedByX = points.clone();
        Point[] sortedByY = points.clone();
        java.util.Arrays.sort(sortedByX, (a, b) -> Double.compare(a.x, b.x));
        java.util.Arrays.sort(sortedByY, (a, b) -> Double.compare(a.y, b.y));

        return closestPairRec(sortedByX, sortedByY, metrics);
    }

    // Recursive closest pair calculation
    private static double closestPairRec(Point[] sortedByX, Point[] sortedByY, Metrics metrics) {
        return closestPairRec(sortedByX, sortedByY, 0, sortedByX.length - 1, metrics);
    }

    private static double closestPairRec(Point[] sortedByX, Point[] sortedByY, int left, int right, Metrics metrics) {
        if (right - left <= 3) {
            return bruteForce(sortedByX, left, right, metrics);
        }

        int mid = (left + right) / 2;
        Point midPoint = sortedByX[mid];

        // Recursively find closest pair in both halves
        Point[] leftY = filterByY(sortedByY, left, mid);
        Point[] rightY = filterByY(sortedByY, mid + 1, right);

        double leftDist = closestPairRec(sortedByX, leftY, left, mid, metrics);
        double rightDist = closestPairRec(sortedByX, rightY, mid + 1, right, metrics);

        double minDist = Math.min(leftDist, rightDist);

        // Check if there's a closer pair across the midpoint
        return stripClosest(sortedByY, midPoint, minDist, metrics);
    }

    // Brute force method for small subarrays
    private static double bruteForce(Point[] points, int left, int right, Metrics metrics) {
        double min = Double.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                metrics.incrementComparisons();
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    // Filter the sorted points by y-coordinate
    private static Point[] filterByY(Point[] sortedByY, int left, int right) {
        Point[] filtered = new Point[right - left + 1];
        int index = 0;
        for (int i = left; i <= right; i++) {
            filtered[index++] = sortedByY[i];
        }
        return filtered;
    }

    // Check the strip for the closest pair across the middle line
    private static double stripClosest(Point[] sortedByY, Point midPoint, double minDist, Metrics metrics) {
        double min = minDist;
        for (int i = 0; i < sortedByY.length; i++) {
            for (int j = i + 1; j < sortedByY.length && (sortedByY[j].y - sortedByY[i].y) < minDist; j++) {
                metrics.incrementComparisons();
                min = Math.min(min, distance(sortedByY[i], sortedByY[j]));
            }
        }
        return min;
    }

    // Distance function between two points
    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}
