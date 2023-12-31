package com.maskSchedule.maskSchedule.controllers;

        import com.maskSchedule.maskSchedule.data.EmployeeRepository;
        import com.maskSchedule.maskSchedule.data.RoleRepository;
        import com.maskSchedule.maskSchedule.data.ShiftRepository;
        import com.maskSchedule.maskSchedule.models.Employee;
        import com.maskSchedule.maskSchedule.models.Role;
        import com.maskSchedule.maskSchedule.models.Shift;
        import jakarta.validation.Valid;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.Errors;
        import org.springframework.web.bind.annotation.*;

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
    public String displayShiftIndex(Model model){
        model.addAttribute("title","Shift Main");
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("shifts",shiftRepository.findAll());
        return "shifts/index";
    }

    @GetMapping("add")
    public String displayAddShift(Model model) {
        model.addAttribute("title", "Create Shift");
        model.addAttribute(new Shift());
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
        return "shifts/add";
    }

    @PostMapping("add")
    public String processingCreateShiftForm(@ModelAttribute @Valid Shift newShift,
                                            Errors errors, Model model, @RequestParam Employee employee, Role role) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Shift");
            return "shifts/add";
        }
//
//        None of this below is likely needed; keeping just in case

//        model.addAttribute("employee", employeeRepository.findAll());
//        model.addAttribute("role", roleRepository.findAll());

//        List<Employee> employeeObject = (List<Employee>) employeeRepository.findAll();
//        model.addAttribute("employee", employeeObject);
//
//        List<Role> roleObject = (List<Role>) roleRepository.findAll();
//        model.addAttribute("role", roleObject);

        shiftRepository.save(newShift);
        return "redirect:/shifts";
    }

    @GetMapping("view/{shiftId}")
    public String displayViewShift(Model model, @PathVariable int shiftId) {

        Optional<Shift> optionalShift = shiftRepository.findById(shiftId);
        if (optionalShift.isPresent()) {
            Shift shift = (Shift) optionalShift.get();
            model.addAttribute("shift", shift);
            return "shifts/view";
        } else {
            return "redirect:../";
        }

    }



}
