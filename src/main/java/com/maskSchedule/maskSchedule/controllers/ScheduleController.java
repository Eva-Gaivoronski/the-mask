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
        int cDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        int numWeeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

        ArrayList<ArrayList<Integer>> month = new ArrayList<>();
        int day = 1;

        for (int i = 0; i < numWeeks; i++) {
            ArrayList<Integer> week = new ArrayList<>();
            if (i == 0) {
                cal.set(cYear, cMonth, 1);
               int startDay = cal.getFirstDayOfWeek();
               for (int j = 0; j < startDay; j++) {
                   week.add(null);
               }
               for (int k = startDay; k < 7; k++){
                   week.add(day);
                   day++;
               }
            }
            if (i != 0){
                for (int l = 0; l < 7; l++) {
                    if (day <= cDays){
                        week.add(day);
                        day++;
                    }

                }
            }

            month.add(week);
        }



        return month;
    }

    @GetMapping
    public String displayScheduleIndex(Model model) {
       model.addAttribute("title", "Schedule Main");
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model) {
        ArrayList<ArrayList<Integer>> month = calendarArr();
        model.addAttribute("title", "Create Schedule");
        model.addAttribute("month",month);
        return "schedule/create";
    }



}
