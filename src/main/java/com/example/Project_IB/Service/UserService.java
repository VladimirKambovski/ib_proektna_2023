package com.example.Project_IB.Service;

import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    List<User> getUsersByUserType(UserType userType);

    void saveUser(User user);

    void deleteUser(Long userId);

    // New methods for fund withdrawal and deposit
    void withdrawFunds(Long userId, double amount);

    void depositFunds(Long userId, double amount);

    void registerUser(User user);
}
