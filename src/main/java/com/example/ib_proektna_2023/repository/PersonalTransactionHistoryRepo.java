package com.example.ib_proektna_2023.repository;

import com.example.ib_proektna_2023.model.PersonalTransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalTransactionHistoryRepo extends JpaRepository<PersonalTransactionHistory,String> {
    List<PersonalTransactionHistory> findAllByUser_BankAccountNumber(Long bankAccountNumber);
}
