package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.*;
import com.maskSchedule.maskSchedule.models.Day;
import com.maskSchedule.maskSchedule.models.Employee;
import com.maskSchedule.maskSchedule.models.Month;
import com.maskSchedule.maskSchedule.models.Year;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private YearRepository yearRepository;
    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private WeekRepository weekRepository;
    @Autowired
    private DayRepository dayRepository;


    @GetMapping
    public String displayScheduleIndex(Model model) {
       model.addAttribute("title", "Schedule Main");
       model.addAttribute("years", yearRepository.findAll());
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model) {
        Calendar cal = new GregorianCalendar();
        //Year newYear = new Year());
        Integer selectedMonth = cal.get(Calendar.MONTH);
        model.addAttribute("title", "Create Schedule");
        //model.addAttribute("year", newYear);
        model.addAttribute("selectedMonth",selectedMonth);
        model.addAttribute("select", selectedMonth);
        model.addAttribute("year",new Year(cal.get(Calendar.YEAR)));
        return "schedule/create";
    }

    @PostMapping("create")
    public String createScheduleProcessing(@ModelAttribute("year") int createYear, Model model) {
        Year newYear = new Year(createYear);
            yearRepository.save(newYear);
            return "redirect:/schedule";
    }

    @GetMapping("edit/{yearId}")
    public String displayEditSchedule(Model model, @PathVariable int yearId) {
        Calendar cal = new GregorianCalendar();
        Integer selectedMonth = cal.get(Calendar.MONTH);
        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            Year year = (Year) optYear.get();
            model.addAttribute("year", year);
            model.addAttribute("yearId", yearId);
            model.addAttribute("title", "Edit Schedule");
            model.addAttribute("selectedMonth",selectedMonth);
            model.addAttribute("select", selectedMonth);
            model.addAttribute("employees", employeeRepository.findAll());
            yearRepository.save(year);
            return "schedule/edit";
        } else {
            return "schedule/edit";
        }

    }

    @PostMapping(value= "edits/{yearId}")
    public String displayEditScheduleProcessing(Model model, @RequestParam(name = "yearId") Integer yearId, @RequestParam(name = "selectedMonth", required = false) int selectedMonth, @RequestParam(name = "day") Day day) {
        Calendar cal = new GregorianCalendar();
        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            Year year = (Year) optYear.get();
            System.out.println(day);
            model.addAttribute("year", year);
            model.addAttribute("title", "Edit Schedule");
            model.addAttribute("selectedMonth",selectedMonth);
            model.addAttribute("select", selectedMonth);
            model.addAttribute("employees", employeeRepository.findAll());
            yearRepository.save(year);
            return "schedule/edit";
        } else {
            return "schedule/edit";
        }

    }

    @GetMapping("assign/{dayId}")
    public String displayAssignEmployee (Model model,@PathVariable int dayId) {
        return "schedule/assign";
    }

    @GetMapping("view/{yearId}")
    public String displayViewSchedule(Model model, @PathVariable int yearId) {
        Calendar cal = new GregorianCalendar();
        Integer selectedMonth = cal.get(Calendar.MONTH);

        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            Year year = (Year) optYear.get();
            model.addAttribute("year", year);
            model.addAttribute("title", "Edit Schedule");
            model.addAttribute("selectedMonth",selectedMonth);
            model.addAttribute("select", selectedMonth);
            model.addAttribute("employees", employeeRepository.findAll());
            return "schedule/view";
        } else {
            return "redirect:../";
        }

    }


}
