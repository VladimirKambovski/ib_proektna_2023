package com.example.Project_IB.Web;

import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager; // Inject the AuthenticationManager

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login"; // Return the login page template (login.html)
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        // You can customize the authentication logic based on your requirements.
        // Here, we use Spring Security's built-in AuthenticationManager to perform authentication.

        try {
            // Attempt to authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // If authentication is successful, store the user details in the session
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Redirect the user to a role-specific page based on the assigned role
            User user = (User) authentication.getPrincipal();
            if (user.getUserType() == UserType.ADMIN) {
                return "redirect:/admin/dashboard";
            } else if (user.getUserType() == UserType.EMPLOYEE) {
                return "redirect:/employee/dashboard";
            } else {
                return "redirect:/user/dashboard";
            }
        } catch (AuthenticationException e) {
            // Authentication failed; handle the error as needed
            return "redirect:/login?error=true";
        }
    }
}
