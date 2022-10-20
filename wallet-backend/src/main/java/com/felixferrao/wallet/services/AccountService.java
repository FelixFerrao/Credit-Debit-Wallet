package com.felixferrao.wallet.services;

import com.felixferrao.wallet.entities.Account;
import com.felixferrao.wallet.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account createOrUpdate(Account account) {
        Optional<Account> accountOptional = accountRepository.findByEmail(account.getEmail());
        if(accountOptional.isPresent()) {
            return null;
        }
        if(account.getId() == null) {
            accountRepository.save(account);
        } else {
            accountRepository.save(account);
        }
        return account;
    }
    public Account getAccountDetailsByEmail(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if(account.isPresent()) {
            return account.get();
        }
        return null;
    }
}
