package com.maskSchedule.maskSchedule.controllers;

import com.maskSchedule.maskSchedule.data.AccountRepository;
import com.maskSchedule.maskSchedule.data.EmployeeRepository;
import com.maskSchedule.maskSchedule.data.ShiftRepository;
import com.maskSchedule.maskSchedule.models.Account;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @GetMapping("")
    public String displayAccounts (Model model, HttpSession session) {
       // model.addAttribute("title", "Account");
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "accounts/index";
    }

    @GetMapping("add")
    public String displayAddForm (Model model) {
        model.addAttribute("title", "Add Account");
        model.addAttribute(new Account());
        return "accounts/add";
    }

    @PostMapping("add")
    public String processAddForm (@ModelAttribute @Valid Account newAccount, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "accounts/add";
        }
        accountRepository.save(newAccount);
        return "redirect:/accounts";
    }

    @GetMapping("delete")
    public String displayDeleteAccount (Model model, HttpSession session) {
        model.addAttribute("title", "Delete Account");
        model.addAttribute("accounts", accountRepository.findAll());
        model.addAttribute("loggedIn", session.getAttribute("user") != null);
        return "accounts/delete";
    }

//    @GetMapping("delete")
//    public String processDeleteAccount (@RequestParam(required = false) int[] accountIds) {
//        if(accountIds !=null) {
//            for (int id : accountIds) {
//                accountRepository.deleteById(id);
//            }
//        }
//        return "redirect:/accounts";
//    }


    @GetMapping("edit/{accountId}")
    public String displayAccount(Model model, @PathVariable int accountId, HttpSession session) {
        Optional <Account> optAccount = accountRepository.findById(accountId);
        if (optAccount.isPresent()) {
            Account account = (Account) optAccount.get();
            model.addAttribute("account", account);
            model.addAttribute("loggedIn", session.getAttribute("user") != null);
            return "accounts/edit";
        } else {
            return "redirect:/";
        }
    }

}
