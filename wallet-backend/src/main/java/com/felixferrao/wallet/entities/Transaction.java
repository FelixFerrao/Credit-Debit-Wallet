package com.felixferrao.wallet.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NotNull(message = "The amount should not be blank")
    private Double amount;
    private String reason;
    @NotNull(message = "Provide type of transaction as 1 - Credit, 0 - Debit")
    private int transactionType;
    private LocalDate transactionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Wallet wallet;

    @PrePersist
    private void setDate() {this.transactionDate = LocalDate.now();}

}
