package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomerManagementService {
    List<User> getAllCustomers();
    Optional<User> getCustomerById(Long id);
    void removeCustomer(Long id);

    // Add more methods for customer management, if needed
}