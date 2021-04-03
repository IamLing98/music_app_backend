package com.music.backend.controller;

import com.music.backend.model.Account;
import com.music.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/oauth2/getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/oauth2/getAll/{email}")
    public ResponseEntity<?> getOne(@PathVariable("email") String email) {
        return new ResponseEntity<>(accountService.getAccountByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/user/me/{id}")
//    @PreAuthorize("hasRole('USER')")
    public Account getAccountById(@PathVariable("id") Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
    public Account getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            System.out.println("User principal name =" + userPrincipal.getUsername());
            System.out.println("Is user enabled =" + userPrincipal.isEnabled());
            return accountService.getAccountByEmail(userPrincipal.getUsername());
        }
        return null;
    }

}
