package com.example.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    
    private final AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    // Check if User exists
    public boolean userExists(Integer accountId) {
    	boolean isPresent = false;
    	isPresent = accountRepository.existsById(accountId);
    	return isPresent;
    }

    // Insert a record
    /**
     * User Story 3 - INSERT a message record in the database for message creation, return the message created
     * @param message
     */ 
	public Message insertMessage(Message message) {
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
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        Message message = optionalMessage.orElse(null);
        return message;
    }

    public Integer deleteMessage(Integer messageId) {
    	messageRepository.deleteById(messageId);
    	int numDeleted = (int) messageRepository.count();
        Integer gonner = numDeleted;
        return gonner;
    }
    
    public boolean messageExists(Integer messageId) {
    	boolean isPresent = false;
    	isPresent = messageRepository.existsById(messageId);
    	return isPresent;
    }

    /**
     * User Story 7: Update message
     * @param id - id of message to be updated
     * @param repplacement - updated text for messageText
     */
    public Message updateMessage(Integer id, String replacement){
        
        // Create new Message object and set fields to replacement values
        Message message = this.getMessageById(id);
        message.setMessageText(replacement);
        
        // Save it
        return messageRepository.save(message);
    }

    /**
     * User Story 8: - Find all messages by a particular user
     * 
     * @param postedBy - accountId for messages
     * @return list of messages posted by that accountId
     */
    public List<Message> getUserMessages(Integer postedBy){
    	return messageRepository.findByPostedBy(postedBy);
    }

}
