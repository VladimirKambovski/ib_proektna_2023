package com.example.ib_proektna_2023.service.impl;

import com.example.ib_proektna_2023.model.DepositsWithdrawals;
import com.example.ib_proektna_2023.repository.DepositsWithdrawalsRepository;
import com.example.ib_proektna_2023.service.DepositsWithdrawalsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DepositsWithdrawalsServiceImpl implements DepositsWithdrawalsService {

    private final DepositsWithdrawalsRepository depositsWithdrawalsRepository;

    public DepositsWithdrawalsServiceImpl(DepositsWithdrawalsRepository depositsWithdrawalsRepository) {
        this.depositsWithdrawalsRepository = depositsWithdrawalsRepository;
    }

    @Override
    public DepositsWithdrawals makeDeposit(DepositsWithdrawals deposit) {
        // Check if the deposit amount is positive
        if (deposit.getDepositAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        // Update account balance with deposit amount
        BigDecimal newBalance = deposit.getAccountBalance().add(deposit.getDepositAmount());
        deposit.setAccountBalance(newBalance);

        // Save the deposit entry
        return depositsWithdrawalsRepository.save(deposit);
    }
    @Override
    public DepositsWithdrawals makeWithdrawal(DepositsWithdrawals withdrawal) {
        // Check if the withdrawal amount is positive and not exceeding the account balance
        if (withdrawal.getWithdrawalAmount().compareTo(BigDecimal.ZERO) <= 0 ||
                withdrawal.getWithdrawalAmount().compareTo(withdrawal.getAccountBalance()) > 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }

        // Update account balance with withdrawal amount
        BigDecimal newBalance = withdrawal.getAccountBalance().subtract(withdrawal.getWithdrawalAmount());
        withdrawal.setAccountBalance(newBalance);

        // Save the withdrawal entry
        return depositsWithdrawalsRepository.save(withdrawal);
    }

    // Add more methods for deposit/withdrawal management if needed
}