package com.felixferrao.wallet.services;

import com.felixferrao.wallet.entities.Account;
import com.felixferrao.wallet.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public Account createOrUpdate(Account account) {
        if(account.getId() == null) {
            accountRepository.save(account);
        } else {
            accountRepository.save(account);
        }
        return account;
    }
}
