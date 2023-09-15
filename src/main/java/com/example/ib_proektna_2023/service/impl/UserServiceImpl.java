package com.example.ib_proektna_2023.service.impl;
import com.example.ib_proektna_2023.model.DepositsWithdrawals;
import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import com.example.ib_proektna_2023.repository.PersonalTransactionHistoryRepository;
import com.example.ib_proektna_2023.repository.UserRepository;
import com.example.ib_proektna_2023.service.UserService;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PersonalTransactionHistoryRepository personalTransactionHistoryRepository;

    public UserServiceImpl(UserRepository userRepository, PersonalTransactionHistoryRepository personalTransactionHistoryRepository) {
        this.userRepository = userRepository;
        this.personalTransactionHistoryRepository = personalTransactionHistoryRepository;
    }

    @Override
    public void makeDeposit(BigDecimal userBalance, BigDecimal depositAmount) {
        // Update user's account balance
        BigDecimal newBalance = userBalance.add(depositAmount);

        // Create a DepositsWithdrawals entry
        DepositsWithdrawals deposit = new DepositsWithdrawals();
        deposit.setDepositAmount(depositAmount);
        deposit.setWithdrawalAmount(BigDecimal.ZERO); // No withdrawal
        deposit.setAccountBalance(newBalance);
// Save the deposit entry
        personalTransactionHistoryRepository.save(deposit);

        // Update the user's account balance
        // You may need to load the user from userRepository, update the balance, and save it back
        // User user = userRepository.findById(userId).orElse(null);
        // if (user != null) {
        //     user.setAccountBalance(newBalance);
        //     userRepository.save(user);
        // }
    }

    @Override
    public void makeWithdrawal(BigDecimal userBalance, BigDecimal withdrawalAmount) {
        // Check if withdrawal amount is valid (positive and within balance)
        if (withdrawalAmount.compareTo(BigDecimal.ZERO) > 0 && withdrawalAmount.compareTo(userBalance) <= 0) {
            // Update user's account balance
            BigDecimal newBalance = userBalance.subtract(withdrawalAmount);

            // Create a DepositsWithdrawals entry
            DepositsWithdrawals withdrawal = new DepositsWithdrawals();
            withdrawal.setDepositAmount(BigDecimal.ZERO); // No deposit
            withdrawal.setWithdrawalAmount(withdrawalAmount);
            withdrawal.setAccountBalance(newBalance);

            // Save the withdrawal entry
            personalTransactionHistoryRepository.save(withdrawal);

            // Update the user's account balance
            // You may need to load the user from userRepository, update the balance, and save it back
            // User user = userRepository.findById(userId).orElse(null);
            // if (user != null) {
            //     user.setAccountBalance(newBalance);
            //     userRepository.save(user);
            // }
        } else {
            // Handle invalid withdrawal amount (e.g., display an error message)
        }
    }



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