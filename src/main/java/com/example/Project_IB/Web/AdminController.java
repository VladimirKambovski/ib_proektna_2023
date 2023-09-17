package com.example.Project_IB.Web;

import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import com.example.Project_IB.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/employee-management")
    public String listEmployees(Model model) {
        model.addAttribute("employees", userService.getUsersByUserType(UserType.EMPLOYEE));
        return "Admin";
    }

    @GetMapping("/employee-management/{employeeId}")
    public String viewEmployee(@PathVariable Long employeeId, Model model) {
        User employee = userService.getUserById(employeeId);
        model.addAttribute("employee", employee);
        return "Admin";
    }

    @GetMapping("/employee-management/{employeeId}/delete")
    public String deleteEmployee(@PathVariable Long employeeId) {
        userService.deleteUser(employeeId);
        return "redirect:/admin/employee-management";
    }

}
