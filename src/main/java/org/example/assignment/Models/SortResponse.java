package org.example.assignment.Models;

import java.util.Arrays;


public class SortResponse {
    private String algorithmName;
    private int[] originalData;
    private int[] sortedData;
    private long executionTimeMs;
    private String timeComplexity;
    private boolean success;
    private String message;

    public SortResponse() {
    }

    public SortResponse(String algorithmName, int[] originalData, int[] sortedData,
                        long executionTimeMs, String timeComplexity) {
        this.algorithmName = algorithmName;
        this.originalData = originalData;
        this.sortedData = sortedData;
        this.executionTimeMs = executionTimeMs;
        this.timeComplexity = timeComplexity;
        this.success = true;
    }

    // Getters and Setters
    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public int[] getOriginalData() {
        return originalData;
    }

    public void setOriginalData(int[] originalData) {
        this.originalData = originalData;
    }

    public int[] getSortedData() {
        return sortedData;
    }

    public void setSortedData(int[] sortedData) {
        this.sortedData = sortedData;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(long executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }

    public String getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(String timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SortResponse{" +
                "algorithmName='" + algorithmName + '\'' +
                ", originalData=" + Arrays.toString(originalData) +
                ", sortedData=" + Arrays.toString(sortedData) +
                ", executionTimeMs=" + executionTimeMs +
                ", timeComplexity='" + timeComplexity + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}