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
    @RequestMapping("/EmployeeManagement")
    public class EmployeeManagementController {

        private final UserService userService;

        public EmployeeManagementController(UserService userService) {
            this.userService = userService;
        }

        @GetMapping("/list")
        public String listEmployees(Model model) {
            // Retrieve a list of all employees
            List<User> employees = userService.findAllEmployees();

            // Add the list of employees to the model
            model.addAttribute("employees", employees);

            return "employee_list"; // Create a corresponding Thymeleaf template for displaying the list
        }

        // Add more methods for managing employees as needed, such as removing employees, viewing their details, etc.
    }
