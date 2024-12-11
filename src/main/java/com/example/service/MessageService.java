package com.example.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Insert a record
    /**
     * User Story 3 - INSERT a message record in the database for message creation, return the message created
     * @param message
     */ 
	public Message insertMessage(Message message, Integer messageId) {
	    return messageRepository.save(message);
	}
	
	// JPA/HIbernate -> database to make static list
	//public List<Message> findAll()
    /**
     * User Story 4 - Retrieves all messages from the database
     * @return a list of all messages
     */
    public List<Message> getAllMessages() { 
        return messageRepository.findAll(); 
    }

    // Get message by id
    public Message getMessageById(Integer messageId) {
    	return messageRepository.selectMessageById(messageId);
    }

    public long deleteMessage(Integer messageId) {
    	messageRepository.deleteById(messageId);
    	return messageRepository.count();
    }
    
    public boolean messageExists(Integer messageId) {
    	boolean isPresent = false;
    	isPresent = messageRepository.existsById(messageId);
    	return isPresent;
    }

}
