package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.EmployeeRepository;
import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.models.Employee;
import com.maskSchedule.maskSchedule.models.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String displayEmployeeIndex(Model model) {
        model.addAttribute("title", "Employee Main");
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees/index";
    }

    @GetMapping("add")
    public String displayAddEmployee(Model model) {
        model.addAttribute("title", "Create Employee");
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute(new Employee());

        return "employees/add";
    }

    @PostMapping("add")
    public String processingAddEmployeeForm(@ModelAttribute @Valid Employee newEmployee,
                                            Errors errors, Model model, @RequestParam List<Integer> role) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Employee");
            return "employees/add";
        }


        List<Role> roleObject = (List<Role>) roleRepository.findAllById(role);
        newEmployee.setRole(roleObject);
        model.addAttribute("role", roleObject);

        employeeRepository.save(newEmployee);
        return "redirect:/employees";
    }

    @GetMapping("edit/{employeeId}")
    public String displayEmployeeEditForm(Model model, @PathVariable int employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            Employee employee = (Employee) optionalEmployee.get();
            model.addAttribute("employee", employee);
            return "employees/edit";
        }
        return "employees/edit";
    }

    @PostMapping("edit")
    public String processingEmployeeEditForm (Model model, int employeeId, String name) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()) {
            Employee employee = (Employee) optionalEmployee.get();
            model.addAttribute("employee", employee);
            employee.setName(name);
            employeeRepository.save(employee);
            return "redirect:/employees";
        }
        return "redirect:/employees";
    }
}