package com.example.ib_proektna_2023.service;

import com.example.ib_proektna_2023.model.DepositsWithdrawals;

public interface DepositsWithdrawalsService {
    DepositsWithdrawals makeDeposit(DepositsWithdrawals deposit);
    DepositsWithdrawals makeWithdrawal(DepositsWithdrawals withdrawal);

    // Add more methods for deposit/withdrawal management if needed
}