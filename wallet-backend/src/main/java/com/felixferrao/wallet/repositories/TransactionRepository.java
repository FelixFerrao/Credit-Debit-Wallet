package com.felixferrao.wallet.repositories;

import com.felixferrao.wallet.entities.Wallet;
import com.felixferrao.wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWallet(Wallet wallet);
}
