package com.felixferrao.wallet.services;

import com.felixferrao.wallet.entities.Account;
import com.felixferrao.wallet.entities.Transaction;
import com.felixferrao.wallet.repositories.AccountRepository;
import com.felixferrao.wallet.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public List<Transaction> getAllTransactions(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()) {
            return transactionRepository.findByAccount(account.get());
        }
        return null;
    }

    public Transaction creditTransact(Long accountId, Transaction transaction) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() + transaction.getAmount());
            transaction.setAccount(account.get());
            transaction.setTransactionType(1);
            transactionRepository.save(transaction);
            accountRepository.save(account.get());
            return transaction;
        }
        return null;
    }

    public Transaction debitTransact(Long accountId, Transaction transaction) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()) {
            account.get().setBalance(account.get().getBalance() - transaction.getAmount());
            transaction.setAccount(account.get());
            transaction.setTransactionType(0);
            transactionRepository.save(transaction);
            accountRepository.save(account.get());
            return transaction;
        }
        return null;
    }
}
