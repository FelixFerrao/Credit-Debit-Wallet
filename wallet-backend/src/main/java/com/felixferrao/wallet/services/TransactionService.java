package com.felixferrao.wallet.services;

import com.felixferrao.wallet.entities.Wallet;
import com.felixferrao.wallet.entities.Transaction;
import com.felixferrao.wallet.repositories.WalletRepository;
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
    WalletRepository walletRepository;

    public List<Transaction> getAllTransactions(Long walletId) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()) {
            return transactionRepository.findByWallet(wallet.get());
        }
        return null;
    }

    public Transaction creditTransact(Long walletId, Transaction transaction) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()) {
            wallet.get().setBalance(wallet.get().getBalance() + transaction.getAmount());
            transaction.setWallet(wallet.get());
            transaction.setTransactionType(1);
            transactionRepository.save(transaction);
            walletRepository.save(wallet.get());
            return transaction;
        }
        return null;
    }

    public Transaction debitTransact(Long walletId, Transaction transaction) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if(wallet.isPresent()) {
            wallet.get().setBalance(wallet.get().getBalance() - transaction.getAmount());
            transaction.setWallet(wallet.get());
            transaction.setTransactionType(0);
            transactionRepository.save(transaction);
            walletRepository.save(wallet.get());
            return transaction;
        }
        return null;
    }
}
