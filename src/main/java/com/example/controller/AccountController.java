package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.service.AccountService;

@RestController
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }
    
}
