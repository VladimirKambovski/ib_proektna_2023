package com.example.ib_proektna_2023.model;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class PersonalTransactionHistory {

    private long bankAccountNumber;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    private User Customer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DepositsWithdrawals> depositsWithdrawalsList = new ArrayList<>();

    // Constructors, getters, setters
    public Long getBankAccountNumber() {
        return bankAccountNumber;
    }
    public void setBankAccountNumber(Long bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
    public PersonalTransactionHistory(User Customer) {
        this.Customer = Customer;
    }

    public PersonalTransactionHistory() {

    }
}
