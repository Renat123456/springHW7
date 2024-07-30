package com.example.demo.rest;

import com.example.demo.model.TimeSheetEntity;
import com.example.demo.service.TimeSheetDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data/timesheets")
public class TimeSheetDataController {
    private final TimeSheetDataService timeSheetDataService;

    public TimeSheetDataController(TimeSheetDataService timeSheetDataService) {
        this.timeSheetDataService = timeSheetDataService;
    }

    @GetMapping("")
    public ResponseEntity<List<TimeSheetEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(timeSheetDataService.getAll());
    }

    @GetMapping("/{id}")
    public TimeSheetEntity getById(@PathVariable Long id){
        return timeSheetDataService.getTimeSheet(id);
    }

    @PostMapping("")
    public TimeSheetEntity addById(@RequestBody TimeSheetEntity timeSheet){
        return timeSheetDataService.addTimeSheet(timeSheet);
    }

    @PutMapping("/{id}")
    public TimeSheetEntity getById(@PathVariable Long id, @RequestBody TimeSheetEntity timeSheet){
        return timeSheetDataService.upTimeSheet(id, timeSheet);
    }

    @DeleteMapping("/{id}")
    public void delById(@PathVariable Long id){
        timeSheetDataService.delTimeSheet(id);
    }
}
