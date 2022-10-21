package com.felixferrao.wallet.controller;

import com.felixferrao.wallet.entities.Transaction;
import com.felixferrao.wallet.services.TransactionService;
import com.felixferrao.wallet.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    ValidationErrorService validationService;

    @GetMapping("/{wallet_id}")
    public ResponseEntity<?> getAllTransactions(@PathVariable Long wallet_id) {
        return new ResponseEntity<>(transactionService.getAllTransactions(wallet_id), HttpStatus.OK);
    }

    @PostMapping("/credit/{wallet_id}")
    public ResponseEntity<?> addMoneyToWallet(@PathVariable Long wallet_id,
                                              @RequestBody @Valid Transaction transaction,
                                              BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Transaction transactionSaved = transactionService.creditTransact(wallet_id, transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.OK);
    }

    @PostMapping("/debit/{wallet_id}")
    public ResponseEntity<?> debitMoneyFromWallet(@PathVariable Long wallet_id,
                                                  @RequestBody @Valid Transaction transaction,
                                                  BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Transaction transactionSaved = transactionService.debitTransact(wallet_id, transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.OK);
    }
}
