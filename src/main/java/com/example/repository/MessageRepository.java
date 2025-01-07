package com.example.repository;

import java.util.List;

// Import JpaRepository and Message entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.Message;

/**
 * A Repository interface that retrieves Message data from the database, using Spring Data JPA
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

	// Method that retrieves a message by user AccountID, referenced with postedBy
	List<Message> findByPostedBy(Integer postedBy);
}
