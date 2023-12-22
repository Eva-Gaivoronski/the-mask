package com.maskSchedule.maskSchedule.controllers;

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
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;

        import java.util.List;

@Controller
@RequestMapping("/shift")
public class ShiftController {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private UserController userController;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("new")
    public String processingCreateShiftForm(@ModelAttribute @Valid Shift newShift,
                                            Errors errors, Model model, @RequestParam List<Integer> role) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Shift");
            return "shift/new";
        }


//        List<Role> roleObject = (List<Role>) roleRepository.findAllById(role);
//        newShift.setRole(roleObject);
//        model.addAttribute("role", roleObject);

        shiftRepository.save(newShift);
        return "redirect:/new";
    }

}
