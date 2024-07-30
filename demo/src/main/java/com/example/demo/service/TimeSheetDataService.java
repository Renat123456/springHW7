package com.example.demo.service;

import com.example.demo.model.TimeSheetEntity;
import com.example.demo.repository.TimeSheetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSheetDataService {
    private TimeSheetRepository timeSheetRepository;

    public TimeSheetDataService(TimeSheetRepository timeSheetRepository) {
        this.timeSheetRepository = timeSheetRepository;
    }

    public List<TimeSheetEntity> getAll(){
        return timeSheetRepository.findAll();
    }

    public TimeSheetEntity getTimeSheet(Long id) {
        return timeSheetRepository.findById(id).get();
    }

    public TimeSheetEntity addTimeSheet(TimeSheetEntity timeSheet) {
        timeSheet.setCreatedAt(LocalDate.now());
        timeSheetRepository.save(timeSheet);
        return timeSheet;
    }

    public void delTimeSheet(Long id) {
        timeSheetRepository.deleteById(id);
    }

    public TimeSheetEntity upTimeSheet(Long id, TimeSheetEntity timeSheet) {
        timeSheet.setId(id);
        timeSheet.setCreatedAt(LocalDate.now());
        timeSheetRepository.save(timeSheet);
        return timeSheet;
    }
}
