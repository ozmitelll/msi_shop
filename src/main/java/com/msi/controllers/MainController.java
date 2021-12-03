package com.msi.controllers;

import com.msi.models.Product;
import com.msi.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productRepo.findAll();
        model.addAttribute("products",productList);
        return "home";
    }

}
