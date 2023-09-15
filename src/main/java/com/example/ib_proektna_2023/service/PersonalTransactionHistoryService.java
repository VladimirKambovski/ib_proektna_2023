package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.PersonalTransactionHistory;

import java.util.List;

public interface PersonalTransactionHistoryService {
    List<PersonalTransactionHistory> getByBankAccountNumber(Long BankAccountNumber);
    PersonalTransactionHistory save(PersonalTransactionHistory personalTransactionHistory);

    // Add more methods for personal transaction history management if needed
}