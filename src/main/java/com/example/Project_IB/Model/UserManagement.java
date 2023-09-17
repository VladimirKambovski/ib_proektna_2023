package com.example.Project_IB.Model;

import com.example.Project_IB.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagement {
    private final UserRepository userRepository;

    public UserManagement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllCustomers() {
        return userRepository.findAllByUserType(UserType.CUSTOMER);
    }

    public void deleteCustomer(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Check if the user is a CUSTOMER
        if (user.getUserType() != UserType.CUSTOMER) {
            throw new IllegalArgumentException("User is not a CUSTOMER");
        }

        userRepository.delete(user);
    }
}
