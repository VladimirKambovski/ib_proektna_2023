package com.example.ib_proektna_2023.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity(name = "is_user")
public class User {

    @Id
    private Long bankAccountNumber;

    @Enumerated(value = EnumType.STRING)
    private UserType type;

    private String name;
    private String password;

    @OneToOne(mappedBy = "user")
    private PersonalTransactionHistory personalTransactionHistory;

    private BigDecimal accountBalance;

    public void makeDeposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            accountBalance = accountBalance.add(amount);
            // Create a DepositsWithdrawals entry and add it to personalTransactionHistory
            DepositsWithdrawals deposit = new DepositsWithdrawals(this, amount, BigDecimal.ZERO, accountBalance);
            personalTransactionHistory.getDepositsWithdrawalsList().add(deposit);
        }
    }

    // Method for making a withdrawal
    public void makeWithdrawal(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(accountBalance) <= 0) {
            accountBalance = accountBalance.subtract(amount);
            // Create a DepositsWithdrawals entry and add it to personalTransactionHistory
            DepositsWithdrawals withdrawal = new DepositsWithdrawals(this, BigDecimal.ZERO, amount, accountBalance);
            personalTransactionHistory.getDepositsWithdrawalsList().add(withdrawal);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return bankAccountNumber.equals(user.bankAccountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "BankAccountNumber=" + bankAccountNumber +
                ", name='" + name + '\'' +
                '}';
    }

    public void setBankAccountNumber(long l) {
    }
}