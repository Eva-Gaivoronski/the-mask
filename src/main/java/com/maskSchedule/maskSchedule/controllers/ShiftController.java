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

        import java.util.ArrayList;
        import java.util.List;

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
        return "shifts/add";
    }

    @PostMapping("add")
    public String processingCreateShiftForm(@ModelAttribute @Valid Shift newShift,
                                            Errors errors, Model model, @RequestParam Employee employee, Role role) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Shift");
            return "add";
        }

        List<String> daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");
        model.addAttribute("daysOfWeek", daysOfWeek);

        List<Employee> employeeObject = (List<Employee>) employeeRepository.findAll();
//        Employee employeeObject = (Employee) employeeRepository.findAll();
//        newShift.setEmployee(employeeObject);
        model.addAttribute("employee", employeeObject);

        List<Role> roleObject = (List<Role>) roleRepository.findAll();
//        Role roleObject = (Role) roleRepository.findAll();
//        newShift.setRole(roleObject);
        model.addAttribute("role", roleObject);

        shiftRepository.save(newShift);
        return "redirect:/shifts";
    }



}
