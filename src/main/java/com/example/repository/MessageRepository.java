package com.example.repository;

import java.util.List;

// Import JpaRepository and Message entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    // Select a message by id
    // @Query(
    //     value = "SELECT * FROM message m WHERE m.messageId = :messageId", 
    //     nativeQuery = true)
    // Message selectMessageById(Integer messageId);

    // void deleteById(Integer messageId);

	List<Message> findByPostedBy(Integer postedBy);
}
