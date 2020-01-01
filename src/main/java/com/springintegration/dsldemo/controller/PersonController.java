package com.springintegration.dsldemo.controller;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springintegration.dsldemo.dto.PersonDto;
import com.springintegration.dsldemo.service.PersonService;

@RestController
public class PersonController {
	
	private final MessageChannel findByIdRequestChannel;
	
	private final MessageChannel createRequestChannel;
	
	public PersonController(@Qualifier("findByIdRequest") MessageChannel findByIdRequestChannel,
			@Qualifier("createRequest") MessageChannel createRequestChannel) {
		this.createRequestChannel = createRequestChannel;
		this.findByIdRequestChannel = findByIdRequestChannel;
	}

	@Autowired
	PersonService person;
	
	@GetMapping("/person")
	List<PersonDto> read() {
		return person.read();
	}
	
	@GetMapping("/person/{id}")
	PersonDto findById(@PathVariable Integer id) {
		
		// Prepare input parameters
		Message<Integer> message = MessageBuilder.withPayload(id).build();
		
		// Send the input message prepared above to service activator end-point
		findByIdRequestChannel.send(message);
		
		//TODO: implement receiving message from output channel
		return null;
	}
	
	
	@PostMapping("/person")
	void create(@RequestBody PersonDto personDto) {
		if ((null != personDto.getFirstName()) && (null != personDto.getLastName())) {
			
			// Prepare input parameters
			Message<PersonDto> message = MessageBuilder.withPayload(personDto).build();
			
			createRequestChannel.send(message);
			
		} else {
			throw new ValidationException("Person cannot be created due to missing values.");
		}
	}
	
}
