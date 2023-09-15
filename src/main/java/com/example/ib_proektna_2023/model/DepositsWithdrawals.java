package com.example.ib_proektna_2023.model;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
public class DepositsWithdrawals {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    private User user;

    private BigDecimal depositAmount;

    private BigDecimal withdrawalAmount;

    private BigDecimal accountBalance;

    // Constructors, getters, setters

    public DepositsWithdrawals(User user, BigDecimal depositAmount, BigDecimal withdrawalAmount, BigDecimal accountBalance) {
        this.user = user;
        this.depositAmount = depositAmount;
        this.withdrawalAmount = withdrawalAmount;
        this.accountBalance = accountBalance;
    }

    public DepositsWithdrawals() {

    }
}
