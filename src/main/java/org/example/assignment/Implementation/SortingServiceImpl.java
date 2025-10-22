package org.example.assignment.Implementation;

import org.example.assignment.Algorithms.SortingAlgorithm;
import org.example.assignment.Models.DataSet;
import org.example.assignment.Models.SortResponse;
import org.example.assignment.Service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class SortingServiceImpl implements SortingService {

    // In-memory storage for datasets
    private final Map<String, DataSet> dataSetRepository = new ConcurrentHashMap<>();

    // Injected sorting algorithms
    private final SortingAlgorithm bubbleSort;
    private final SortingAlgorithm quickSort;
    private final SortingAlgorithm selectionSort;

    @Autowired
    public SortingServiceImpl(
            @Qualifier("bubbleSort") SortingAlgorithm bubbleSort,
            @Qualifier("quickSort") SortingAlgorithm quickSort,
            @Qualifier("selectionSort") SortingAlgorithm selectionSort) {
        this.bubbleSort = bubbleSort;
        this.quickSort = quickSort;
        this.selectionSort = selectionSort;


        initializeSampleData();
    }

    private void initializeSampleData() {
        DataSet sample1 = new DataSet("Small Dataset",
                new int[]{64, 34, 25, 12, 22, 11, 90},
                "Small random numbers for testing");

        DataSet sample2 = new DataSet("Large Dataset",
                new int[]{100, 87, 56, 43, 29, 15, 8, 72, 91, 64, 38, 21, 5, 83, 47},
                "Larger dataset for performance comparison");

        DataSet sample3 = new DataSet("Nearly Sorted",
                new int[]{1, 2, 3, 5, 4, 6, 7, 8, 9, 10},
                "Nearly sorted dataset");

        dataSetRepository.put(sample1.getId(), sample1);
        dataSetRepository.put(sample2.getId(), sample2);
        dataSetRepository.put(sample3.getId(), sample3);
    }

    @Override
    public SortResponse sortData(String algorithmType, int[] data) {
        if (data == null || data.length == 0) {
            SortResponse errorResponse = new SortResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Data cannot be null or empty");
            return errorResponse;
        }

        SortingAlgorithm algorithm = getAlgorithm(algorithmType);

        if (algorithm == null) {
            SortResponse errorResponse = new SortResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Invalid algorithm type: " + algorithmType);
            return errorResponse;
        }


        int[] originalData = data.clone();


        long startTime = System.currentTimeMillis();
        int[] sortedData = algorithm.sort(data);
        long endTime = System.currentTimeMillis();

        return new SortResponse(
                algorithm.getAlgorithmName(),
                originalData,
                sortedData,
                endTime - startTime,
                algorithm.getTimeComplexity()
        );
    }

    private SortingAlgorithm getAlgorithm(String type) {
        if (type == null) {
            return null;
        }


        switch (type.toLowerCase()) {
            case "bubble":
            case "bubblesort":
                return bubbleSort;
            case "quick":
            case "quicksort":
                return quickSort;
            case "selection":
            case "selectionsort":
                return selectionSort;
            default:
                return null;
        }
    }

    @Override
    public DataSet createDataSet(DataSet dataSet) {
        if (dataSet.getId() == null || dataSet.getId().isEmpty()) {
            dataSet.setId(UUID.randomUUID().toString());
        }
        dataSetRepository.put(dataSet.getId(), dataSet);
        return dataSet;
    }

    @Override
    public List<DataSet> getAllDataSets() {
        return new ArrayList<>(dataSetRepository.values());
    }

    @Override
    public DataSet getDataSetById(String id) {
        return dataSetRepository.get(id);
    }

    @Override
    public DataSet updateDataSet(String id, DataSet dataSet) {
        if (dataSetRepository.containsKey(id)) {
            dataSet.setId(id);
            dataSetRepository.put(id, dataSet);
            return dataSet;
        }
        return null;
    }

    @Override
    public boolean deleteDataSet(String id) {
        return dataSetRepository.remove(id) != null;
    }

    @Override
    public List<String> getAvailableAlgorithms() {
        return Arrays.asList(
                bubbleSort.getAlgorithmName(),
                quickSort.getAlgorithmName(),
                selectionSort.getAlgorithmName()
        );
    }
}