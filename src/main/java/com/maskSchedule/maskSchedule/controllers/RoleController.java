package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.models.Employee;
import com.maskSchedule.maskSchedule.models.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("roles")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String displayRoleIndex(Model model) {
        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        return "roles/index";
    }

    @GetMapping("add")
    public String displayAddRole(Model model) {
        model.addAttribute("title", "Create Role");
        model.addAttribute(new Role());

        return "roles/add";
    }

    @PostMapping("add")
    public String processingAddRoleForm(@ModelAttribute @Valid Role newRole,
                                        Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Role");
            return "roles/add";
        }
        System.out.println(newRole);

        roleRepository.save(newRole);
        return "redirect:/roles";
    }

    @GetMapping("edit/{roleId}")
    public String displayRoleEditForm(Model model, @PathVariable int roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()){
            Role role = (Role) optionalRole.get();
            model.addAttribute("role", role);
            return "roles/edit";
        }
        return "roles/edit";
    }

    @PostMapping("edit")
    public String processingRoleEditForm (Model model, int roleId, String name) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role role = (Role) optionalRole.get();
            model.addAttribute("role", role);
            role.setName(name);
            roleRepository.save(role);
            return "redirect:/roles";
        }
        return "redirect:/roles";
    }
}