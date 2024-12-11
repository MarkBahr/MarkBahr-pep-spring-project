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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping(path = "/message")
	public Message getMessage() {
		return new Message(1234, "Message text", 234987865l);
	}
	
	@GetMapping("/messages")
	public List<Message> retrieveAllMessages() {
		return messageService.getAllMessages();
	}

	@GetMapping("/messages/{messageId}") 
	public Message getMessageById(Integer messageId){
		return messageService.getMessageById(messageId);
	}

	@DeleteMapping("messages/{messageId}")
	public ResponseEntity<Long> deleteMessage(@PathVariable Integer messageId) {
		Long num = messageService.deleteMessage(messageId);
		if (num < 1) {
			return new ResponseEntity<>(num, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
