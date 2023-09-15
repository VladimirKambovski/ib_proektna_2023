package com.example.ib_proektna_2023.service.impl;
import com.example.ib_proektna_2023.model.DepositsWithdrawals;
import com.example.ib_proektna_2023.model.PersonalTransactionHistory;
import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.repository.PersonalTransactionHistoryRepository;
import com.example.ib_proektna_2023.repository.UserRepository;
import com.example.ib_proektna_2023.service.PersonalTransactionHistoryService;
import com.example.ib_proektna_2023.service.UserService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserRepository userRepository;
    private final PersonalTransactionHistoryRepository personalTransactionHistoryRepository;


    private User getCurrentUser() {
        // Get the currently authenticated user from the SecurityContext
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            // Cast the principal to UserDetails to access user details
            UserDetails userDetails = (UserDetails) principal;

            // Retrieve the username or other user information to identify the user
            String username = userDetails.getUsername();

            // Implement a method to find and return the User object based on the username
            return userRepository.findByUsername(username);
        }

        // Handle cases where the user is not authenticated or other scenarios
        return null; // You can adjust the return type and handling based on your requirements
    }

    public UserServiceImpl(UserRepository userRepository, PersonalTransactionHistoryRepository personalTransactionHistoryRepository) {
        this.userRepository = userRepository;
        this.personalTransactionHistoryRepository = personalTransactionHistoryRepository;
    }

   @Override
    public void makeDeposit(BigDecimal userBalance, BigDecimal depositAmount) {
       // Update user's account balance
       BigDecimal newBalance = userBalance.add(depositAmount);

       // Create a DepositsWithdrawals entry
       User user = getCurrentUser(); // Implement a method to get the current user
       PersonalTransactionHistory personalTransactionHistory = user.getPersonalTransactionHistory();

       // Create a new deposit entry
       DepositsWithdrawals deposit = new DepositsWithdrawals(user, depositAmount, BigDecimal.ZERO, newBalance);

       // Save the deposit entry to personalTransactionHistory
       personalTransactionHistory.getDepositsWithdrawalsList().add(deposit);
       personalTransactionHistoryRepository.save(personalTransactionHistory);

       // Update the user's account balance
       user.setAccountBalance(newBalance);
       userRepository.save(user);
   }


    @Override
    public void makeWithdrawal(BigDecimal userBalance, BigDecimal withdrawalAmount) {
        // Check if withdrawal amount is valid (positive and within balance)
        if (withdrawalAmount.compareTo(BigDecimal.ZERO) > 0 && withdrawalAmount.compareTo(userBalance) <= 0) {
            // Update user's account balance
            BigDecimal newBalance = userBalance.subtract(withdrawalAmount);

            // Create a DepositsWithdrawals entry
            User user = getCurrentUser(); // Implement a method to get the current user
            PersonalTransactionHistory personalTransactionHistory = user.getPersonalTransactionHistory();

            // Create a new withdrawal entry and add it to personalTransactionHistory
            DepositsWithdrawals withdrawal = new DepositsWithdrawals(user, BigDecimal.ZERO, withdrawalAmount, newBalance);
            personalTransactionHistory.getDepositsWithdrawalsList().add(withdrawal);

            // Save the withdrawal entry to personalTransactionHistory
            personalTransactionHistoryRepository.save(personalTransactionHistory);

            // Update the user's account balance
            user.setAccountBalance(newBalance);
            userRepository.save(user);
        } else {
            // Handle invalid withdrawal amount (e.g., display an error message)
        }
    }



//    @Override
//    public void makeWithdrawal(BigDecimal userBalance, BigDecimal withdrawalAmount) {
//        // Check if withdrawal amount is valid (positive and within balance)
//        if (withdrawalAmount.compareTo(BigDecimal.ZERO) > 0 && withdrawalAmount.compareTo(userBalance) <= 0) {
//            // Update user's account balance
//            BigDecimal newBalance = userBalance.subtract(withdrawalAmount);
//
//            // Create a DepositsWithdrawals entry
//            User user = getCurrentUser(); // Implement a method to get the current user
//            DepositsWithdrawals withdrawal = new DepositsWithdrawals(user, BigDecimal.ZERO, withdrawalAmount, newBalance);
//
//            // Save the withdrawal entry to personalTransactionHistory
//            personalTransactionHistoryRepository.save(withdrawal);
//
//            // Update the user's account balance
//            user.setAccountBalance(newBalance);
//            userRepository.save(user);
//        } else {
//            // Handle invalid withdrawal amount (e.g., display an error message)
//        }
//    }



    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findById(Long.parseLong(username)).orElseThrow(Exception::new);
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(" ")
                .roles(u.getType().name())
                .build();
    }



    @Override
    public User getUserByBankAccountNumber(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllCustomers() {
        return userRepository.findAllByType(UserType.CUSTOMER);
    }
    public List<User> findAllEmployees() {
        return userRepository.findAllByType(UserType.EMPLOYEE);
    }




}