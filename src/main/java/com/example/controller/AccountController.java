package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

@RestController
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }
    
    @GetMapping("accounts")
	public List<Account> retrieveAllAccounts() {
		return service.getAllAccounts();
	}
	
	@PostMapping("register")
	public ResponseEntity<Account> createAccount(String username, String password) {
		
		String str = "";
		
		// If duplicate username
		if(service.usernameExists(username) == false) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} else if (username.equals(str)) {
			return ResponseEntity.badRequest().body(null);
		} else if (password.length() > 3){
			// If username is not blank, password length >= 4, & account does not exist, return account
            return ResponseEntity.badRequest().body(null);
		} else {			
			// add acount
			Account addAcct = new Account(username, password);
			
			// return account
			//Account newAccount = service.persistAccount(addAcct);
			
			return ResponseEntity.status(HttpStatus.OK).body(addAcct);
		}
		
	}
}
