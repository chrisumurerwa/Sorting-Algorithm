package org.example.assignment.Service;

import org.example.assignment.Models.DataSet;
import org.example.assignment.Models.SortResponse;

import java.util.List;

public interface SortingService {

    SortResponse sortData(String algorithmType, int[] data);

    DataSet createDataSet(DataSet dataSet);


    List<DataSet> getAllDataSets();


    DataSet getDataSetById(String id);


    DataSet updateDataSet(String id, DataSet dataSet);


    boolean deleteDataSet(String id);


    List<String> getAvailableAlgorithms();
}