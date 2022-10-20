package com.felixferrao.wallet.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate = new Date();
    @NotNull(message = "The transaction type should be defined")
    @Min(0)
    @Max(1)
    private int transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
