package com.music.backend.service;

import com.music.backend.model.Account;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface AccountService {

    Account getAccountById(Long id);

    Account getAccountByEmail(String email);

    List<Account> getAll();

}
