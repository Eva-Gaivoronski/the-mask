package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.EmployeeRepository;
import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.data.ShiftRepository;
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

    //Calendar information

    public ArrayList<ArrayList<Integer>> calendarArr() {
        Calendar cal = new GregorianCalendar();
        int cDay = cal.get(Calendar.DATE);
        int cMonth = cal.get(Calendar.MONTH);
        int cYear = cal.get(Calendar.YEAR);

        int numWeeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        ArrayList<ArrayList<Integer>> month = new ArrayList<>();

        ArrayList<Integer> week = null;
        for (int i = 0; i < numWeeks; i++) {
            week = new ArrayList<>();

        }
        month.add(week);


        return month;
    }

    @GetMapping
    public String displayScheduleIndex(Model model) {
       model.addAttribute("title", "Schedule Main");
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model) {
        model.addAttribute("title", "Create Schedule");
        model.addAttribute("weeks", cal.getActualMaximum(Calendar.WEEK_OF_MONTH));
        return "schedule/create";
    }



}
