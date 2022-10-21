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
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email address should not be blank")
    private String email;
    @NotBlank(message = "Account name cannot be blank")
    private String name;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private Double balance = new Double(0);
}
