package com.music.backend.service.impl;

import com.music.backend.base.userdetails.UserDetailsPrincipal;
import com.music.backend.exception.ResourceNotFoundException;
import com.music.backend.model.Account;
import com.music.backend.model.ConfirmToken;
import com.music.backend.repository.AccountRepository;
import com.music.backend.repository.ConfirmTokenRepository;
import com.music.backend.service.AccountService;
import com.music.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements UserDetailsService, AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ConfirmTokenRepository confirmTokenRepository;

    @Autowired
    EmailService emailService;

    @Transactional
    public UserDetails loadUserById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserDetailsPrincipal.create(account);
    }

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
        System.out.println("Accout:");
        System.out.print(account);
        try {
            ConfirmToken confirmationToken = new ConfirmToken(account);

            confirmTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(account.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("linhdeptrai1029ii@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://localhost:8082/confirm-account?token=" + confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);
        } catch (Exception ex) {
            System.out.println("Email send has an error");
            System.out.println(ex);
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findFirstByEmail(email);
        if (account == null) throw new UsernameNotFoundException("User not found with email : " + email);
        return UserDetailsPrincipal.create(account);
    }

}
