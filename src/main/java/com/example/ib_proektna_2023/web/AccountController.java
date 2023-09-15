package com.example.ib_proektna_2023.web;


import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * A controller managing the Users. Acessible to ADMIN role. (See SecurityConfig)
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        List<String> roles = Arrays.stream(UserType.values()).map(UserType::name).toList();
        model.addAttribute("roles",roles);
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String name,
                           @RequestParam UserType role){
        User u = new User();
        u.setBankAccountNumber(Long.parseLong(username));
        u.setName(name);
        u.setPassword(" ");
        u.setType(role);
        userService.saveUser(u);
        return "redirect:/";
    }


}