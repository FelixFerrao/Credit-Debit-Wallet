package com.felixferrao.wallet.controller;

import com.felixferrao.wallet.entities.Wallet;
import com.felixferrao.wallet.services.WalletService;
import com.felixferrao.wallet.services.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private ValidationErrorService validationService;

    @GetMapping
    public ResponseEntity<?> getAllWallets() {
        return new ResponseEntity<>(walletService.getAllWallets(), HttpStatus.OK);
    }

    @GetMapping("/{email_id}")
    public ResponseEntity<?> findDetailsByEmail(@PathVariable String email_id) {
        Wallet wallet = walletService.getWalletDetailsByEmail(email_id);
        if(wallet != null) return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
        return new ResponseEntity<>("Account Not Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result) {
        ResponseEntity errors = validationService.validate(result);
        if(errors != null) return errors;
        Wallet walletSaved = walletService.createOrUpdate(wallet);
        if(walletSaved != null) { return new ResponseEntity<Wallet>(walletSaved, HttpStatus.CREATED); }
        return new ResponseEntity<>("Account already exists!", HttpStatus.BAD_REQUEST);

    }
}
