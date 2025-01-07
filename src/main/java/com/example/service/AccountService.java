package com.example.service;

import java.util.ArrayList;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

/**
 * Service class for Accounts
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	
    /**
     * Use the method from AccountRepository to access data.
     * @param username
     * @param password
     * @return the account by specified username and password
     */
    public Account getAccount(String username, String password) {
    	return accountRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Method to determine if an account exists by specified username.
     * @param username
     * @return true if account exists, return false if it does not exist.
     */
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


    /**
     * Method for retrieving a list of all accounts in the database
     * @return list of all accounts
     */
    public List<Account> getAllAccounts() { 
        return accountRepository.findAll(); 
    }

	
    /**
     * Method for obtaining a user by username and password
     * @param username
     * @param password
     * @throws AuthenticationException
     */
    public void login(String username, String password) throws AuthenticationException {
    	accountRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Method for creating accounts upon user registration
     * @param account (does not include accountId)
     * @return the newly created account
     */
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }
		
}
