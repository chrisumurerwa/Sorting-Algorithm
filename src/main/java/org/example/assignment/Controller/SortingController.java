package org.example.assignment.Controller;

import org.example.assignment.Models.DataSet;
import org.example.assignment.Models.SortResponse;
import org.example.assignment.Service.SortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/sorting")
@CrossOrigin(origins = "*")
public class SortingController {

    private final SortingService sortingService;

    @Autowired
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }


    @GetMapping("/algorithms")
    public ResponseEntity<List<String>> getAlgorithms() {
        return ResponseEntity.ok(sortingService.getAvailableAlgorithms());
    }


    @PostMapping("/sort")
    public ResponseEntity<SortResponse> sortData(
            @RequestParam String algorithm,
            @RequestBody int[] data) {

        SortResponse response = sortingService.sortData(algorithm, data);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/datasets/{id}/sort")
    public ResponseEntity<?> sortDataSet(
            @PathVariable String id,
            @RequestParam String algorithm) {

        DataSet dataSet = sortingService.getDataSetById(id);

        if (dataSet == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dataset not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        SortResponse response = sortingService.sortData(algorithm, dataSet.getData());

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }


    @GetMapping("/datasets")
    public ResponseEntity<List<DataSet>> getAllDataSets() {
        return ResponseEntity.ok(sortingService.getAllDataSets());
    }


    @GetMapping("/datasets/{id}")
    public ResponseEntity<?> getDataSetById(@PathVariable String id) {
        DataSet dataSet = sortingService.getDataSetById(id);

        if (dataSet == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dataset not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.ok(dataSet);
    }


    @PostMapping("/datasets")
    public ResponseEntity<DataSet> createDataSet(@RequestBody DataSet dataSet) {
        DataSet created = sortingService.createDataSet(dataSet);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @PutMapping("/datasets/{id}")
    public ResponseEntity<?> updateDataSet(
            @PathVariable String id,
            @RequestBody DataSet dataSet) {

        DataSet updated = sortingService.updateDataSet(id, dataSet);

        if (updated == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dataset not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/datasets/{id}")
    public ResponseEntity<?> deleteDataSet(@PathVariable String id) {
        boolean deleted = sortingService.deleteDataSet(id);

        if (!deleted) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dataset not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Dataset deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "Sorting Service");
        return ResponseEntity.ok(health);
    }
}