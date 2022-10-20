package com.felixferrao.wallet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Account name cannot be blank")
    private String name;
    @Size(min = 3, max = 30)
    private String accountNumber;
    private Double balance = new Double(0);;
    @Size(min = 10, max = 100)
    private String description;
}
