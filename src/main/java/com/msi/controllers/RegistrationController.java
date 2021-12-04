package com.msi.controllers;

import com.msi.models.AuthentificationProvider;
import com.msi.models.User;
import com.msi.repositories.UserRepo;
import com.msi.services.MailSender;
import com.msi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Autowired
    private MailSender mailSender;


    @GetMapping("/registration")
    public String registrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String username,
                               @RequestParam String password,
                               @RequestParam String email, Model model) {
        System.out.println(username + " - " + password);
        if (!userRepo.existsByUsername(username)) {
            User user = new User(username, password, email);
            user.setActive(true);
            user.setActivationCode(UUID.randomUUID().toString());
            user.setAuthentificationProvider(AuthentificationProvider.LOCAL);
            userRepo.save(user);

            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "Hello, %s! \n" +
                                "Welcome to Sweater. Please, visit next link: http://localhost:8086/activate/%s",
                        user.getUsername(),
                        user.getActivationCode()

                );
            }
            return "redirect:/login";
        } else {
            //  model.addAttribute("message", "This user is have in DB");
            return "registration";
        }

    }

    @GetMapping("/acitvate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isAcivated = userService.activateUser(code);

        if(isAcivated){
            System.out.println("activated!");
        } else {
            System.out.println("activation code not found");
        }

        return "login";
    }


}
