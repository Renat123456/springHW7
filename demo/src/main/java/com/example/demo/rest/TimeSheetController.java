package com.example.demo.rest;

import com.example.demo.model.TimeSheet;
import com.example.demo.service.TimeSheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/timesheets")
public class TimeSheetController {
    private final TimeSheetService timeSheetService;

    public TimeSheetController(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String name) {
        return "hello " + name;
    }

    @GetMapping("")
    public ResponseEntity<List<TimeSheet>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(timeSheetService.getALL());
    }

    @GetMapping("/{id}")
    public TimeSheet getById(@PathVariable Long id){
        return timeSheetService.getTimeSheet(id);
    }

    @PostMapping("")
    public TimeSheet addById(@RequestBody TimeSheet timeSheet){
        return timeSheetService.addTimeSheet(timeSheet);
    }

    @PutMapping("/{id}")
    public TimeSheet getById(@PathVariable Long id, @RequestBody TimeSheet timeSheet){
        return timeSheetService.upTimeSheet(id, timeSheet);
    }

    @DeleteMapping("/{id}")
    public void delById(@PathVariable Long id){
        timeSheetService.delTimeSheet(id);
    }
}
