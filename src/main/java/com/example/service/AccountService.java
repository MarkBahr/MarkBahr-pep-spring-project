package com.example.service;

import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	
    // Get user Account
    /**
     * 
     * @param username
     * @param password
     * @return
     */
    public Account getAccount(String username, String password) {
    	return accountRepository.findByUsernameAndPassword(username, password);
    }
    
    public boolean usernameExists(String username) {
    	boolean exists = false;
    	List<Account> accounts = new ArrayList<Account>();
        accounts = accountRepository.findAll();
        for(Account acct : accounts) {
            if(acct.getUsername().equals(username)) {
                exists = true;
            }
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


//	public Optional<Account> findById(Integer accountId) {
//		// TODO Auto-generated method stub
//		return accountRepository.findById(accountId);
//	}
//	
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }
		
}