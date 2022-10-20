package com.felixferrao.wallet.controller;

import com.felixferrao.wallet.entities.Account;
import com.felixferrao.wallet.services.AccountService;
import com.felixferrao.wallet.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ValidationErrorService validationService;

    @GetMapping
    public ResponseEntity<?> getAllWallets() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Account accountSaved = accountService.createOrUpdate(account);
        return new ResponseEntity<Account>(accountSaved, HttpStatus.CREATED);
    }
}
