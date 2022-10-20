package com.felixferrao.wallet.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    @PrePersist
    private void setDate() {this.transactionDate = new Date();}

}
