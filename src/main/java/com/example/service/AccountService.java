package com.example.service;

import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.*;
// import com.example.entity.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}