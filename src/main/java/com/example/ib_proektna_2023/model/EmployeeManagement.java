package com.example.ib_proektna_2023.model;
import java.math.BigDecimal;
public class EmployeeManagement { private Long bankAccountNumber;
    private String name;
    private BigDecimal accountBalance;

    public EmployeeManagement(Long bankAccountNumber, String name, BigDecimal accountBalance) {
        this.bankAccountNumber = bankAccountNumber;
        this.name = name;
        this.accountBalance = accountBalance;
    }



    public Long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(Long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
