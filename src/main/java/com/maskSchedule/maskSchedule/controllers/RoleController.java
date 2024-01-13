package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.RoleRepository;
import com.maskSchedule.maskSchedule.models.Employee;
import com.maskSchedule.maskSchedule.models.Role;
import jakarta.servlet.http.HttpSession;
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
    public String displayRoleIndex(Model model, HttpSession session) {
        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "roles/index";
    }

    @GetMapping("add")
    public String displayAddRole(Model model, HttpSession session) {
        model.addAttribute("title", "Create Role");
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        model.addAttribute(new Role());

        return "roles/add";
    }

    @PostMapping("add")
    public String processingAddRoleForm(@ModelAttribute @Valid Role newRole,
                                        Errors errors, Model model,
                                        HttpSession session) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Role");
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
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

    @GetMapping("view/{roleId}")
    public String displayViewRole(Model model, @PathVariable int roleId) {

        Optional<Role> optRole = roleRepository.findById(roleId);
        if (optRole.isPresent()) {
            Role role = (Role) optRole.get();
            model.addAttribute("role", role);
            return "roles/view";
        } else {
            return "redirect:../";
        }

    }

}