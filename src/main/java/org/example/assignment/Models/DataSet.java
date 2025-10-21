package org.example.assignment.Models;

import java.util.Arrays;
import java.util.UUID;

/**
 * Model class representing a dataset
 */
public class DataSet {
    private String id;
    private String name;
    private int[] data;
    private String description;

    public DataSet() {
        this.id = UUID.randomUUID().toString();
    }

    public DataSet(String name, int[] data, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.data = data;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                ", description='" + description + '\'' +
                '}';
    }
}