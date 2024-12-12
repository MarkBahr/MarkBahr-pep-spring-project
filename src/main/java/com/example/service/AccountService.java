package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
// import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.*;
// import com.example.entity.*;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

        /**
     * 
     * @param username
     * @param password
     * @return
     */
    public Account getAccount(String username, String password) {
    	return accountRepository.findByUsernameAndPassword(username, password);
    }
    
    public Boolean usernameExists(String username) {
    	Boolean exists = false;
    	Account account = accountRepository.findByUsername(username);
    	if(!(account.getUsername() == null)) {
    		exists = true;
    	}
    	return exists;
    }
    
    // Get all accounts
    public List<Account> getAllAccounts() { 
        return accountRepository.findAll(); 
    }
   
    // Login
    public void login(String username, String password) throws AuthenticationException {
    	accountRepository.findByUsernameAndPassword(username, password);
    }


	public Optional<Account> findById(Integer accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findById(accountId);
	}
	
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }
}