package com.music.backend.service.impl;

import com.music.backend.model.Account;
import com.music.backend.repository.AccountRepository;
import com.music.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return account;
        }
        return null;
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = accountRepository.findFirstByEmail(email);
        return account;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
