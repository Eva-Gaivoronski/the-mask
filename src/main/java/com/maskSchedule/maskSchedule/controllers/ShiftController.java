package com.maskSchedule.maskSchedule.controllers;

        import com.maskSchedule.maskSchedule.data.EmployeeRepository;
        import com.maskSchedule.maskSchedule.data.RoleRepository;
        import com.maskSchedule.maskSchedule.data.ShiftRepository;
        import com.maskSchedule.maskSchedule.models.Employee;
        import com.maskSchedule.maskSchedule.models.Role;
        import com.maskSchedule.maskSchedule.models.Shift;
        import jakarta.servlet.http.HttpSession;
        import jakarta.validation.Valid;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.*;
//        import java.util.*;
//        import javax.activation.*;
//        import javax.mail.*;
//        import javax.mail.internet.*;
//        import javax.naming.*;

        import java.time.LocalTime;
        import java.time.temporal.ChronoUnit;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

@Controller
@RequestMapping("shifts")
public class ShiftController {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String displayShiftIndex(Model model, HttpSession session){
        model.addAttribute("title","Shift Main");
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("shifts",shiftRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "shifts/index";
    }

    @GetMapping("add")
    public String displayAddShift(Model model, HttpSession session) {
        model.addAttribute("title", "Create Shift");
        model.addAttribute(new Shift());
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        List<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");
        model.addAttribute("daysOfWeek", daysOfWeek);
        return "shifts/add";
    }

    @PostMapping("add")
    public String processingCreateShiftForm(@ModelAttribute @Valid Shift newShift,
                                            Errors errors, Model model, @RequestParam Employee employee, Role role) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Shift");
            return "shifts/add";
        }

        shiftRepository.save(newShift);
        return "redirect:/shifts";
    }

    @GetMapping("view/{shiftId}")
    public String displayViewShift(Model model, @PathVariable int shiftId, HttpSession session) {

        Optional<Shift> optionalShift = shiftRepository.findById(shiftId);
        if (optionalShift.isPresent()) {
            Shift shift = (Shift) optionalShift.get();
            model.addAttribute("shift", shift);
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
            return "shifts/view";
        } else {
            return "redirect:../";
        }

    }

    @GetMapping("edit/{shiftId}")
    public String displayShiftEditForm(Model model, @PathVariable int shiftId, HttpSession session) {
        Optional<Shift> editShift = shiftRepository.findById(shiftId);
        if (editShift.isPresent()){
            Shift shift = (Shift) editShift.get();

            model.addAttribute("loggedIn", session.getAttribute("user") != null);
            model.addAttribute("title", "Edit Shift");
            model.addAttribute("shift", shift);
            model.addAttribute("employee", employeeRepository.findAll());
            model.addAttribute("role", roleRepository.findAll());
            List<String> daysOfWeek = new ArrayList<>();
            daysOfWeek.add("Monday");
            daysOfWeek.add("Tuesday");
            daysOfWeek.add("Wednesday");
            daysOfWeek.add("Thursday");
            daysOfWeek.add("Friday");
            daysOfWeek.add("Saturday");
            daysOfWeek.add("Sunday");
            model.addAttribute("daysOfWeek", daysOfWeek);

            return "shifts/edit";
        }
        return "shifts/edit";
    }

    @PostMapping("edit")
    public String processingShiftEditForm (@ModelAttribute @Valid Shift shift, Errors errors, @PathVariable int shiftId,
                                           LocalTime shiftStart, LocalTime shiftEnd, String shiftDay, Role role,
                                           Model model, HttpSession session) {

        model.addAttribute("loggedIn", session.getAttribute("user") != null);


        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Shift");
            model.addAttribute("shifts", shiftRepository.findAll());
            return "shifts/edit";
        }

        Optional<Shift> editShift = shiftRepository.findById(shiftId);
        if (editShift.isPresent()) {
            shift = (Shift) editShift.get();

            model.addAttribute("shift", shift);

            shift.setShiftDay(shiftDay);
            shift.setShiftStart(shiftStart);
            shift.setShiftEnd(shiftEnd);
            shift.setRole(role);

            shiftRepository.save(shift);
            return "redirect:/shifts";

        }

        return "redirect:/shifts";

    }

    @GetMapping("delete/{shiftId}")
    public String displayShiftDeleteForm(Model model, @PathVariable int shiftId, HttpSession session) {
        Optional<Shift> deleteShift = shiftRepository.findById(shiftId);
        if (deleteShift.isPresent()){
            Shift shift = (Shift) deleteShift.get();
            model.addAttribute("title", "Delete Shift");
            model.addAttribute("shift", shift);
            model.addAttribute("employee", employeeRepository.findAll());
            model.addAttribute("role", roleRepository.findAll());
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
            List<String> daysOfWeek = new ArrayList<>();
            daysOfWeek.add("Monday");
            daysOfWeek.add("Tuesday");
            daysOfWeek.add("Wednesday");
            daysOfWeek.add("Thursday");
            daysOfWeek.add("Friday");
            daysOfWeek.add("Saturday");
            daysOfWeek.add("Sunday");
            model.addAttribute("daysOfWeek", daysOfWeek);

            return "shifts/delete";
        }
        return "shifts/delete";
    }

    @PostMapping("delete/{shiftId}")
    public String processShiftDeleteForm(@ModelAttribute @Valid Shift shift, Errors errors, @PathVariable int shiftId,Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Delete Shift");
            model.addAttribute("shifts", shiftRepository.findAll());
            return "shifts/delete";
        }
        Optional<Shift> deleteShift = shiftRepository.findById(shiftId);
        if (deleteShift.isPresent()) {
            shift = (Shift) deleteShift.get();

            model.addAttribute("shift", shift);

            shiftRepository.deleteById(shiftId);
            return "redirect:/shifts";
        }
        return "redirect:/shifts";
    }

    @GetMapping("sendSchedule")
    public String displaySendScheduleForm(Model model, HttpSession session) {
        model.addAttribute("title", "Send Schedule");
        model.addAttribute("shift", shiftRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);

        return "shifts/sendSchedule";

    }

    @PostMapping("sendSchedule")
    public String processSendScheduleForm(Model model, HttpSession session) {
        model.addAttribute("title", "Send Schedule");
        model.addAttribute("shift", shiftRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("role", roleRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);

        List<Employee> employeeList = (List<Employee>) employeeRepository.findAll();

        //convert shift data into some sort of JSON format

        for (Employee e : employeeList) {
            //check if there are shifts
            //if there are shifts send them to their emails with API help
        }



        return"redirect:/shifts";
    }

}