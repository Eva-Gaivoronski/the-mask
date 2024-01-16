package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.EmployeeRepository;
import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.data.ShiftRepository;
import com.maskSchedule.maskSchedule.data.YearRepository;
import com.maskSchedule.maskSchedule.models.Year;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private YearRepository yearRepository;


    @GetMapping(value="")
    public String displayScheduleIndex(Model model, HttpSession session) {
       model.addAttribute("title", "Schedule Main");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model, HttpSession session) {
        Calendar cal = new GregorianCalendar();
        Year newYear = new Year(cal.get(Calendar.YEAR));
        Integer selectedMonth = cal.get(Calendar.MONTH);
        model.addAttribute("title", "Create Schedule");
        model.addAttribute("year", newYear);
        model.addAttribute("month",newYear.getMonth(cal.get(Calendar.MONTH)));
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("selectedMonth",selectedMonth);
        model.addAttribute("select", selectedMonth);
        return "schedule/create";
    }

    @PostMapping(value = "results")
    public String processingCreateSchedule(Model model, @RequestParam(name = "selectedMonth") Integer selectedMonth) {
        Calendar cal = new GregorianCalendar();
        Year newYear = new Year(cal.get(Calendar.YEAR));
        //int select = selectedMonth;


        model.addAttribute("title", "Create Schedule");
        model.addAttribute("year", newYear);
        model.addAttribute("selectedMonth",selectedMonth);
        //model.addAttribute("select", select);
        return "schedule/create";
    }


}
