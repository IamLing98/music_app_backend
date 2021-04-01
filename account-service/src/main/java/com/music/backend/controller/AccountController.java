package com.music.backend.controller;

import com.music.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
    }

}
