package com.maskSchedule.maskSchedule.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String redirectToHomePage(){
        return "redirect:/welcome";
    }

    @GetMapping(("/welcome"))
    public String displayHomePage(Model model, HttpSession session){
        model.addAttribute("headingText", "Welcome to Mask Employee Schedule Platform");
        model.addAttribute("loggedIn", session.getAttribute("user") != null );
        return "index";
    }
//    @GetMapping
//    public String displayMainIndex() {
//        return "index";
//    }
}
