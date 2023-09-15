package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.PersonalTransactionHistory;
import com.example.ib_proektna_2023.model.UserType;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void makeWithdrawal(BigDecimal Amount);

    void makeDeposit(BigDecimal Amount);

    public User getUserByBankAccountNumber(Long id) throws Exception;
    public User saveUser(User user);
    List<User> findAllCustomers();

    List<User> findAllEmployees();
    void makeDeposit(BigDecimal accountBalance, BigDecimal depositAmount);
    void makeWithdrawal(BigDecimal accountBalance, BigDecimal withdrawalAmount);
}
