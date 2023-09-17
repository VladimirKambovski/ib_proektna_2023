package com.example.Project_IB.Web;


import com.example.Project_IB.Exception.InsufficientFundsException;
import com.example.Project_IB.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/withdraw")
    public String withdrawFundsForm(@PathVariable Long userId, Model model) {
        // Display a form to withdraw funds
        model.addAttribute("user", userService.getUserById(userId));
        return "Customer";
    }

    @PostMapping("/{userId}/withdraw")
    public String withdrawFunds(@PathVariable Long userId, @RequestParam("amount") double amount) {
        try {
            userService.withdrawFunds(userId, amount);
        } catch (InsufficientFundsException e) {
            // Handle insufficient funds error, you can show an error message to the user
            return "Customer";
        }
        return "redirect:/users/" + userId;
    }

    @GetMapping("/{userId}/deposit")
    public String depositFundsForm(@PathVariable Long userId, Model model) {
        // Display a form to deposit funds
        model.addAttribute("user", userService.getUserById(userId));
        return "Customer";
    }

    @PostMapping("/{userId}/deposit")
    public String depositFunds(@PathVariable Long userId, @RequestParam("amount") double amount) {
        userService.depositFunds(userId, amount);
        return "redirect:/users/" + userId;
    }

}