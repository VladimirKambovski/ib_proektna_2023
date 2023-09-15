package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.PersonalTransactionHistory;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    public User getUserByBankAccountNumber(Long id) throws Exception;
    public User saveUser(User user);
    List<User> findAllCustomers();
    void makeWithdrawal(BigDecimal Amount);
    void makeDeposit(BigDecimal Amount);
}
