package org.example.assignment.Algorithms;

import org.springframework.stereotype.Component;


@Component("selectionSort")
public class SelectionSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] data) {
        if (data == null || data.length <= 1) {
            return data;
        }

        int[] result = data.clone();
        int n = result.length;

        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (result[j] < result[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                int temp = result[i];
                result[i] = result[minIndex];
                result[minIndex] = temp;
            }
        }

        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n²) - Best: O(n²), Average: O(n²), Worst: O(n²)";
    }
}