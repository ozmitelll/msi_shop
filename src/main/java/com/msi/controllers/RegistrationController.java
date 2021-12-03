package com.msi.controllers;

import com.msi.models.User;
import com.msi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email, Model model){
        System.out.println(username + " - " + password);
        if (!userRepo.existsByUsername(username)) {
            User user = new User(username,password,email);
            user.setActive(true);
            userRepo.save(user);
            return "redirect:/login";
        } else{
            model.addAttribute("message", "This user is have in DB");
            return "registration";
        }
    }



}
