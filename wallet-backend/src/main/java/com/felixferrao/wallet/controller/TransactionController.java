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
    static int counter = 0;

    @GetMapping("/{account_id}")
    public ResponseEntity<?> getAllTransactions(@PathVariable Long account_id) {
        counter += 1;
        System.out.println("Hi, I am in GetMapping Controller " + counter);
        return new ResponseEntity<>(transactionService.getAllTransactions(account_id), HttpStatus.OK);
    }

    @PostMapping("/credit/{account_id}")
    public ResponseEntity<?> addMoneyToWallet(@PathVariable Long account_id,
                                              @RequestBody @Valid Transaction transaction,
                                              BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Transaction transactionSaved = transactionService.creditTransact(account_id, transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.OK);
    }

    @PostMapping("/debit/{account_id}")
    public ResponseEntity<?> debitMoneyFromWallet(@PathVariable Long account_id,
                                                  @RequestBody @Valid Transaction transaction,
                                                  BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Transaction transactionSaved = transactionService.debitTransact(account_id, transaction);
        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.OK);
    }

//    @PostMapping("/{account_id}")
//    public ResponseEntity<?> initializeTransaction(@PathVariable Long account_id,
//                                                   @RequestBody @Valid Transaction transaction,
//                                                   BindingResult result) {
//        ResponseEntity errors = validationService.validate(result);
//        if(errors != null) return errors;
//        Transaction transactionSaved = transactionService.transact(account_id, transaction);
//        return new ResponseEntity<Transaction>(transactionSaved, HttpStatus.CREATED);
//    }
}
