package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		
		String str = "";
		
		// If duplicate username
		if(service.usernameExists(account.getUsername()) == true) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		 } else if (account.getUsername().equals(str)) {
		 	return ResponseEntity.badRequest().body(null);
		 } else if (account.getPassword().length() < 4){
		 	return ResponseEntity.badRequest().body(null);
		 	// If username is not blank, password length >= 4, & account does not exist, return account
		 } else {			
			 //	Account regAccount = new Account(username, password); // Account with the new credentials
			Account newAccount = service.createAccount(account);	// Persist the account in the database
			return ResponseEntity.status(HttpStatus.OK).body(newAccount); // Return the account with 200 status
		}
	}
	
	@PostMapping("login")
	public ResponseEntity<Account> accountLogin(@RequestBody Account account) {
		Account confirmAccount =  service.getAccount(account.getUsername(), account.getPassword());
		
		if(confirmAccount == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {			
			return ResponseEntity.status(HttpStatus.OK).body(confirmAccount);
		}
	}	
}
