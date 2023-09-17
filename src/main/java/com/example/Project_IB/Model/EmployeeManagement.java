package com.example.Project_IB.Model;

import com.example.Project_IB.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagement {
    private final UserRepository userRepository;

    public EmployeeManagement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllEmployees() {
        return userRepository.findAllByUserType(UserType.EMPLOYEE);
    }

    public void deleteEmployee(Long userId) {
        // Check if the user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Check if the user is an EMPLOYEE
        if (user.getUserType() != UserType.EMPLOYEE) {
            throw new IllegalArgumentException("User is not an EMPLOYEE");
        }

        // Delete the employee
        userRepository.delete(user);
    }
}
