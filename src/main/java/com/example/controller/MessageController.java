package com.example.controller;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import com.example.service.MessageService;
import com.example.entity.Message;
// import java.util.ArrayList;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	// Hello World
	// "Hello World"
	@GetMapping(path = "/hello")
	public String helloWorld() {
		return "Hello World";
	}
	
	/**
	 * User Story 3: Post a new message. A new message will be persisted if...
	 * 	- 1. messageText not blank AND length <= 255	3. postedBy is actual user
	 * 
	 * @param message 
	 * @return new message if criteria met. Else, bad request status 
	 */
	@PostMapping("messages")
	public ResponseEntity<Message> persistMessage(@RequestBody Message message) {
		
		String str = "";

		// If text not blank, not over 255 characters, and postedBy is a real user, 
		// Then insert the new message and return it with status of 200.
		if(message.getMessageText().equals(str)) {
			return ResponseEntity.badRequest().body(null);
		} else if (message.getMessageText().length() > 255) {
			return ResponseEntity.badRequest().body(null);
		} else if (messageService.userExists(message.getPostedBy()) == false) {
			return ResponseEntity.badRequest().body(null);
		} else {
			Message newMessage = messageService.insertMessage(message);
			return ResponseEntity.status(HttpStatus.OK).body(newMessage);
		}
	}
	
	@GetMapping("/messages")
	public List<Message> retrieveAllMessages() {
		return messageService.getAllMessages();
	}

	@GetMapping("messages/{messageId}") 
	public Message getMessageById(@PathVariable Integer messageId){
		Message message = messageService.getMessageById(messageId);
		return message;
	}

	// This passes the test, but does it actually only delete 1 row?
	@DeleteMapping("messages/{messageId}")
	public ResponseEntity<Integer> deleteMessage(@PathVariable("messageId") Integer messageId) {
		Boolean exists = messageService.messageExists(messageId);
		if(exists) {
			messageService.deleteMessage(messageId);
			Integer num = 1;
			return ResponseEntity.status(HttpStatus.OK).body(num);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	/**
	 * As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}. The request body should contain a new messageText values to replace the message identified by messageId. The request body can not be guaranteed to contain any other information.
	 *
	 * - The update of a message should be successful if and only if the message id already exists and the new messageText 
	 * is not blank and is not over 255 characters. If the update is successful, the response body should contain the number 
	 * of rows updated (1), and the response status should be 200, which is the default. The message existing on the database 
	 * should have the updated messageText.
	 * - If the update of the message is not successful for any reason, the response status should be 400. (Client error)
	 */
	@PatchMapping("messages/{messageId}")
	public ResponseEntity<Integer> patchMessage(@PathVariable("messageId") Integer messageId, @RequestBody String messageText) {
		Boolean exists = messageService.messageExists(messageId);
		String str = "";
		List<Message> msgList = new ArrayList<Message>();
		if(exists == false) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (messageText.equals(str)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else if (messageText.length() > 255) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			Message newMessage = messageService.updateMessage(messageId, messageText);
			msgList.add(newMessage);
			Integer rowsUpdated = msgList.size();
			return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
		}
	}

	/**
	 * User Story 8: Get all messages posted by a particular user.
	 */
	@GetMapping("accounts/{accountId}/messages")
	public List<Message> getUserMessages(@PathVariable Integer accountId) {
		return messageService.getUserMessages(accountId);
	}

}
