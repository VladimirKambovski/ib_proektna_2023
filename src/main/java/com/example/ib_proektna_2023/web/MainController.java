package com.example.ib_proektna_2023.web;

import com.example.ib_proektna_2023.model.PersonalTransactionHistory;
import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller representing the main entry point in the app.
 * Redirects to appropriate controller or lists transactions for the user.
 */
@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByBankAccountNumber(Long.parseLong(username));

        if (user.getType() == UserType.EMPLOYEE) {
            return "redirect:/EmployeeManagement";
        } else if (user.getType() == UserType.ADMIN) {
            return "redirect:/CustomerManagement";
        } else {
            PersonalTransactionHistory personalTransactionHistory = user.getPersonalTransactionHistory();
            model.addAttribute("personalTransactionHistory", personalTransactionHistory);
            model.addAttribute("title", "Your Transactions:");
            return "PersonalTransactionHistory";
        }
    }
}
