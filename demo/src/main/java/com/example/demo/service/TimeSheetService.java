package com.example.demo.service;

import com.example.demo.model.TimeSheet;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSheetService {
    private static Long countId = 1L;
    private final List<TimeSheet> timeSheets = new ArrayList<>();

    public List<TimeSheet> getALL() {
        return timeSheets;
    }

    public TimeSheet getTimeSheet(Long id) {
        return timeSheets.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public TimeSheet addTimeSheet(TimeSheet timeSheet) {
        timeSheet.setId(countId++);
        timeSheet.setCreatedAt(LocalDate.now());
        timeSheets.add(timeSheet);
        return timeSheet;
    }

    public void delTimeSheet(Long id) {
        timeSheets.removeIf(t -> t.getId().equals(id));
    }

    public TimeSheet upTimeSheet(Long id, TimeSheet timeSheet) {
        TimeSheet timeSheetOld = getTimeSheet(id);
        if (timeSheetOld != null) {
            timeSheetOld.setProjectId(timeSheet.getProjectId());
            timeSheetOld.setMinutes(timeSheet.getMinutes());
            timeSheetOld.setCreatedAt(LocalDate.now());
        }
        return timeSheetOld;
    }
}
