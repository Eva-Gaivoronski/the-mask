package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.*;
import com.maskSchedule.maskSchedule.models.Day;
import com.maskSchedule.maskSchedule.models.Employee;
import com.maskSchedule.maskSchedule.models.Month;
import com.maskSchedule.maskSchedule.models.Year;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public String displayScheduleIndex(Model model, HttpSession session) {
       model.addAttribute("title", "Schedule Main");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
       model.addAttribute("years", yearRepository.findAll());
        return "schedule/index";
    }

    @GetMapping("create")
    public String createSchedule(Model model, HttpSession session) {
        Calendar cal = new GregorianCalendar();
        Integer selectedMonth = cal.get(Calendar.MONTH);
        model.addAttribute("title", "Create Schedule");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("selectedMonth",selectedMonth);
        model.addAttribute("select", selectedMonth);
        model.addAttribute("year",new Year(cal.get(Calendar.YEAR)));
        return "schedule/create";
    }

    @PostMapping("create")
    public String createScheduleProcessing(@ModelAttribute @Valid Year newYear, Errors errors,
                                           Model model, HttpSession session) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Employee");
            model.addAttribute("LoggedIn", session.getAttribute("user") != null);
            return "schedule/create";
        }
            yearRepository.save(newYear);
            return "redirect:/schedule";
    }

    @GetMapping("edit/{yearId}")
    public String displayEditSchedule(Model model, HttpSession session, @PathVariable int yearId) {
        Calendar cal = new GregorianCalendar();
        Integer selectedMonth = cal.get(Calendar.MONTH);
        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
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
    public String displayEditScheduleProcessing(Model model, HttpSession session, @RequestParam(name = "yearId") Integer yearId, @RequestParam(name = "selectedMonth", required = false) int selectedMonth) {
        Calendar cal = new GregorianCalendar();
        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
            Year year = (Year) optYear.get();
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
    public String displayAssignEmployee (Model model, HttpSession session,@PathVariable int dayId){
        Optional<Day> thisDay = dayRepository.findById(dayId);
        if (thisDay.isPresent()){
            Day day = (Day) thisDay.get();
             Year year = day.getWeek().getMonth().getYear();
            model.addAttribute("year", year);
            model.addAttribute("day", day);
        }
        model.addAttribute("title", "Assign Employee");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute("employees", employeeRepository.findAll());
        return "schedule/assign";
    }

    @PostMapping("assign/{dayId}")
    public String processDisplayAssignEmployee(Model model, @RequestParam(name = "employee", required = false) Integer employeeId,
                                               @RequestParam(name = "yearId") int yearId, @RequestParam(name = "dayId") int dayId) {
        String returnString = "redirect:/schedule/edit/"+yearId;
        if (employeeId == null) {
            return returnString;
        }
        Optional<Day> thisDay = dayRepository.findById(dayId);
        if (thisDay.isPresent()){
            Day day = (Day) thisDay.get();
            Optional<Employee> thisEmployee = employeeRepository.findById(employeeId);
            if (thisEmployee.isPresent()){
                Employee employee = (Employee) thisEmployee.get();
                day.addEmployee(employee);
                dayRepository.save(day);
            }
        }

        return returnString;
    }

    @GetMapping("view/{yearId}")
    public String displayViewSchedule(Model model, HttpSession session, @PathVariable int yearId) {
        Calendar cal = new GregorianCalendar();
        Integer selectedMonth = cal.get(Calendar.MONTH);

        Optional<Year> optYear = yearRepository.findById(yearId);
        if (optYear.isPresent()) {
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
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
