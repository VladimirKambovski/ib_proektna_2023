package com.example.ib_proektna_2023.service.impl;

import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.repository.UserRepository;
import com.example.ib_proektna_2023.service.EmployeeManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

    private final UserRepository userRepository;

    public EmployeeManagementServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllEmployees() {
        return userRepository.findAllByType(UserType.EMPLOYEE);
    }

    @Override
    public Optional<User> getEmployeeById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void removeEmployee(Long id) {
        userRepository.deleteById(id);
    }

    // Add more methods for employee management, if needed
}