package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.EmployeeRepository;
import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.data.ShiftRepository;
import com.maskSchedule.maskSchedule.models.Year;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ShiftRepository shiftRepository;


    @GetMapping
    public String displayScheduleIndex(Model model) {
       model.addAttribute("title", "Schedule Main");
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model) {
        Calendar cal = new GregorianCalendar();
        Year newYear = new Year(cal.get(Calendar.YEAR));
        model.addAttribute("title", "Create Schedule");
        model.addAttribute("year", newYear);
        model.addAttribute("month",newYear.getMonth(cal.get(Calendar.MONTH)));
        return "schedule/create";
    }



}
