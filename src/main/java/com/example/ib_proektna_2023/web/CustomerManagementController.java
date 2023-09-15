package com.example.ib_proektna_2023.web;

import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/CustomerManagement")
public class CustomerManagementController {

    private final UserService userService;

    public CustomerManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        // Retrieve a list of all customers
        List<User> customers = userService.findAllCustomers();

        // Add the list of customers to the model
        model.addAttribute("customers", customers);

        return "customer_list"; // Create a corresponding Thymeleaf template for displaying the list
    }

    // Add more methods for managing customers as needed, such as removing users, viewing transaction history, etc.
}