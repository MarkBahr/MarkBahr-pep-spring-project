package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Account;

/**
 * A Repository interface that retrieves Account data from the database, using Spring Data JPA
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

	// Method to retrieve an account by the specified username and password.
	public Account findByUsernameAndPassword(String username, String password);

	// Method to find a user by username
	public Account findByUsername(String username);

}
