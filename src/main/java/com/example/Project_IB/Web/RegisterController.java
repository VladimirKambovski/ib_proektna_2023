package com.example.Project_IB.Web;

import com.example.Project_IB.Exception.RegistrationException;
import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import com.example.Project_IB.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final UserService userService; // Inject the UserService to handle registration

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Initialize a new User object for the form
        return "Register"; // Display the registration form template
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            // Perform validation and registration logic in the UserService
            userService.registerUser(user);

            // Set a success message
            redirectAttributes.addAttribute("success", "true");
        } catch (RegistrationException e) {
            // Handle registration failure and set an error message
            redirectAttributes.addAttribute("error", "true");
        }

        // Redirect the user to a role-specific page based on the assigned role
        if (user.getUserType() == UserType.ADMIN) {
            return "redirect:/admin/dashboard";
        } else if (user.getUserType() == UserType.EMPLOYEE) {
            return "redirect:/employee/dashboard";
        } else {
            return "redirect:/user/dashboard";
        }
    }
}