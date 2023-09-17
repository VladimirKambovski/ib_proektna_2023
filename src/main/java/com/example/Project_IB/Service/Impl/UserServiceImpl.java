package com.example.Project_IB.Service.Impl;

import com.example.Project_IB.Exception.InsufficientFundsException;
import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import com.example.Project_IB.Repository.UserRepository;
import com.example.Project_IB.Service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> getUsersByUserType(UserType userType) {
        return userRepository.findAllByUserType(userType);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void withdrawFunds(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        BigDecimal currentBalance = user.getBalance();

        // Convert BigDecimal to double for calculation
        double currentBalanceDouble = currentBalance.doubleValue();

        if (currentBalanceDouble >= amount) {
            // Sufficient funds available, update the balance
            currentBalanceDouble -= amount;

            // Convert back to BigDecimal before saving
            user.setBalance(BigDecimal.valueOf(currentBalanceDouble));
            userRepository.save(user);
        } else {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
    }

    @Override
    public void depositFunds(Long userId, double amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        BigDecimal currentBalance = user.getBalance();

        // Convert BigDecimal to double for calculation
        double currentBalanceDouble = currentBalance.doubleValue();

        // Calculate the new balance
        double newBalanceDouble = currentBalanceDouble + amount;

        // Convert back to BigDecimal before saving
        user.setBalance(BigDecimal.valueOf(newBalanceDouble));
        userRepository.save(user);
    }
    public void registerUser(User user) {
        // Perform validation and save user data
        userRepository.save(user);
    }
}