package org.example.assignment.Algorithms;


public interface SortingAlgorithm {
    /**
     * Sort the given array of integers
     * @param data array to be sorted
     * @return sorted array
     */
    int[] sort(int[] data);


    String getAlgorithmName();


    String getTimeComplexity();
}