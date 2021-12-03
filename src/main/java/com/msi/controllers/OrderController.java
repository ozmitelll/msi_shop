package com.msi.controllers;

import com.msi.models.Product;
import com.msi.models.User;
import com.msi.repositories.ProductRepo;
import com.msi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;
    @GetMapping("/listproduct")
    public String produtList(Model model, Principal principal){
        User user = userRepo.findUsersByUsername(principal.getName());
        model.addAttribute("myUser", user);
        return "listProducts";
    }
    @PostMapping("/addproduct")
    String addProduct(@RequestParam("product") Long idProduct, Principal principal, Model model){
        User user = userRepo.findUsersByUsername(principal.getName());
        Product product = productRepo.findById(idProduct).get();
        user.getOrderList().add(product);

        product.setUser(user);
        productRepo.save(product);
        return "redirect:/";
    }

}
