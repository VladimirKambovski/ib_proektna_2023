package com.example.Project_IB.Web;

import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import com.example.Project_IB.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/customer-management")
    public String listCustomers(Model model) {
        model.addAttribute("customers", userService.getUsersByUserType(UserType.CUSTOMER));
        return "Employee";
    }

    @GetMapping("/customer-management/{customerId}")
    public String viewCustomer(@PathVariable Long customerId, Model model) {
        User customer = userService.getUserById(customerId);
        model.addAttribute("customer", customer);
        return "Employee";
    }

    @PostMapping("/customer-management/{customerId}/delete")
    public String deleteCustomer(@PathVariable Long customerId) {
        userService.deleteUser(customerId);
        return "redirect:/employee/customer-management";
    }

    // Other employee-specific actions for managing customers
}
