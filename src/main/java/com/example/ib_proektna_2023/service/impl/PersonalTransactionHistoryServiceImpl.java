package com.example.ib_proektna_2023.service.impl;

import com.example.ib_proektna_2023.model.PersonalTransactionHistory;
import com.example.ib_proektna_2023.repository.PersonalTransactionHistoryRepository;
import com.example.ib_proektna_2023.service.PersonalTransactionHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTransactionHistoryServiceImpl implements PersonalTransactionHistoryService {

    private final PersonalTransactionHistoryRepository personalTransactionHistoryRepository;

    public PersonalTransactionHistoryServiceImpl(PersonalTransactionHistoryRepository personalTransactionHistoryRepository) {
        this.personalTransactionHistoryRepository = personalTransactionHistoryRepository;
    }

    @Override
    public List<PersonalTransactionHistory> getByBankAccountNumber(Long bankAccountNumber) {
        // Implement logic to retrieve personal transaction history by user ID
        return personalTransactionHistoryRepository.findAllByUser_BankAccountNumber(bankAccountNumber);
    }

    @Override
    public PersonalTransactionHistory save(PersonalTransactionHistory personalTransactionHistory) {
        // Implement logic to save or update personal transaction history entry
        return personalTransactionHistoryRepository.save(personalTransactionHistory);
    }

    // Add more methods for personal transaction history management if needed
}