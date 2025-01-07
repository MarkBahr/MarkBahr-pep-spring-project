package com.example.service;

// Imports
import org.springframework.stereotype.Service;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Message;
import java.util.List;
import java.util.Optional;

/**
 * Service class that services the Message entity
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    
    private final AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Method that checks if a user exists by accountId
     * @param accountId from Account entity
     * returns true if user exists, false otherwise
     */
    public boolean userExists(Integer accountId) {
    	boolean isPresent = false;
    	isPresent = accountRepository.existsById(accountId);
    	return isPresent;
    }

    
    /**
     * Method for User Story 3 - INSERT a message record in the database for message creation.
     * @param message
     * @return the message created
     */ 
	public Message insertMessage(Message message) {
	    return messageRepository.save(message);
	}

	
    /**
     * Method for User Story 4 - Retrieves all messages from the database
     * @return a list of all messages
     */
    public List<Message> getAllMessages() { 
        return messageRepository.findAll(); 
    }

    /**
     * Method that retrieves message by messageId
     * @param messageId from Message entity
     * @return specified message if it exists, if not returns empty optional
     */
    public Message getMessageById(Integer messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        Message message = optionalMessage.orElse(null);
        return message;
    }


    /**
     * Method that deletes a message by messageId
     * @param messageId from Message entity
     * @return the number of deleted messages
     */
    public Integer deleteMessage(Integer messageId) {
    	messageRepository.deleteById(messageId);
    	int numDeleted = (int) messageRepository.count();
        Integer gonner = numDeleted;
        return gonner;
    }

	
    /**
     * Method that checks if a message exists by messageId
     * @param messageId from Message entity
     * @return true if the message exists, false if it does not exist
     */
    public boolean messageExists(Integer messageId) {
    	boolean isPresent = false;
    	isPresent = messageRepository.existsById(messageId);
    	return isPresent;
    }

    /**
     * Method for User Story 7: Update message
     * @param id - id of message to be updated
     * @param repplacement - updated text for messageText
     * @return the persisted (updated) message
     */
    public Message updateMessage(Integer id, String replacement){
        
        // Create new Message object and set fields to replacement values
        Message message = this.getMessageById(id);
        message.setMessageText(replacement);
        
        // Save it
        return messageRepository.save(message);
    }

    /**
     * Method for User Story 8: - Find all messages by a particular user
     * 
     * @param postedBy - accountId for messages
     * @return list of messages posted by that accountId
     */
    public List<Message> getUserMessages(Integer postedBy){
    	return messageRepository.findByPostedBy(postedBy);
    }

}
