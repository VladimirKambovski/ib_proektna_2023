package com.example.ib_proektna_2023.service.impl;

import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.repository.UserRepository;
import com.example.ib_proektna_2023.service.CustomerManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManagementServiceImpl implements CustomerManagementService {

    private final UserRepository userRepository;

    public CustomerManagementServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllCustomers() {
        return userRepository.findAllByType(UserType.CUSTOMER);
    }

    @Override
    public Optional<User> getCustomerById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void removeCustomer(Long id) {
        userRepository.deleteById(id);
    }

    // Add more methods for customer management, if needed
}