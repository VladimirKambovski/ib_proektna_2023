package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.User;

import java.util.List;
import java.util.Optional;

public interface EmployeeManagementService {
    List<User> getAllEmployees();
    Optional<User> getEmployeeById(Long id);
    void removeEmployee(Long id);

    // Add more methods for employee management, if needed
}

