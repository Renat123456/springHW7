package com.example.demo.page;

import com.example.demo.model.Project;
import com.example.demo.model.TimeSheet;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TimeSheetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("timesheets")
public class TimeSheetPageController {
    private TimeSheetService timeSheetService;
    private ProjectService projectService;

    public TimeSheetPageController(TimeSheetService timeSheetService, ProjectService projectService) {
        this.timeSheetService = timeSheetService;
        this.projectService = projectService;
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "hello thymeleaf");
        return "hello";
    }

    @GetMapping
    public String getTimeSheets(Model model){
        model.addAttribute("timesheets", timeSheetService.getALL());
        return "timesheets";
    }

    @GetMapping("/{id}")
    public String getTimeSheets(@PathVariable Long id, Model model){
        TimeSheet timeSheet = timeSheetService.getTimeSheet(id);
        if(timeSheet == null){
            return "redirect:/404.html";
        } else {
            model.addAttribute("timesheet", timeSheet);
            return "timesheet";
        }
    }

    @GetMapping("/projects/{id}")
    public String getProject(@PathVariable Long id, Model model){
        Project project = projectService.getProject(id);
        if(project == null){
            return "redirect:/404.html";
        } else {
            model.addAttribute("project", project);
            return "project";
        }
    }
}