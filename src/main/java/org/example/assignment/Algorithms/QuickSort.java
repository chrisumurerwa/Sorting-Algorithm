package org.example.assignment.Algorithms;
import org.springframework.stereotype.Component;

@Component("quickSort")
public class QuickSort implements SortingAlgorithm {

    @Override
    public int[] sort(int[] data) {
        if (data == null || data.length <= 1) {
            return data;
        }

        int[] result = data.clone();
        quickSort(result, 0, result.length - 1);
        return result;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    @Override
    public String getAlgorithmName() {
        return "Quick Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log n) - Best: O(n log n), Average: O(n log n), Worst: O(n²)";
    }
}