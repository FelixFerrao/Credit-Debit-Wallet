package com.felixferrao.wallet.controller;

import com.felixferrao.wallet.entities.Account;
import com.felixferrao.wallet.services.AccountService;
import com.felixferrao.wallet.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ValidationErrorService validationService;

    @GetMapping
    public ResponseEntity<?> getAllWallets() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{email_id}")
    public ResponseEntity<?> findDetailsByEmail(@PathVariable String email_id) {
        Account account = accountService.getAccountDetailsByEmail(email_id);
        if(account != null) return new ResponseEntity<Account>(account, HttpStatus.OK);
        return new ResponseEntity<>("Account Not Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Account account, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Account accountSaved = accountService.createOrUpdate(account);
        if(accountSaved != null) {return new ResponseEntity<Account>(accountSaved, HttpStatus.CREATED);}
        return new ResponseEntity<>("Account already exists!", HttpStatus.BAD_REQUEST);

    }
}
