package com.music.backend.service;

import com.music.backend.model.Account;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AccountService {

    Account getAccountById(Long id);

    Account getAccountByEmail(String email);

    List<Account> getAll();

    UserDetails loadUserById(Long id);

}
