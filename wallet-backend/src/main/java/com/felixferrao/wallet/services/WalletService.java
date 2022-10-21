package com.felixferrao.wallet.services;

import com.felixferrao.wallet.entities.Wallet;
import com.felixferrao.wallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }
    public Wallet createOrUpdate(Wallet wallet) {
        Optional<Wallet> walletOptional = walletRepository.findByEmail(wallet.getEmail());
        if(walletOptional.isPresent()) {
            return null;
        }
        if(wallet.getId() == null) {
            walletRepository.save(wallet);
        } else {
            walletRepository.save(wallet);
        }
        return wallet;
    }

    public Wallet getWalletDetailsByEmail(String email) {
        Optional<Wallet> wallet = walletRepository.findByEmail(email);
        if(wallet.isPresent()) {
            return wallet.get();
        }
        return null;
    }
}
