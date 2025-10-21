package org.example.assignment.Algorithms;

import org.springframework.stereotype.Component;
@Component("bubbleSort")
public class BubbleSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] data) {
        if (data == null || data.length <= 1) {
            return data;
        }

        int[] result = data.clone();
        int n = result.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (result[j] > result[j + 1]) {
                    // Swap elements
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swapping occurred, array is already sorted
            if (!swapped) {
                break;
            }
        }

        return result;
    }

    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "";
    }


}