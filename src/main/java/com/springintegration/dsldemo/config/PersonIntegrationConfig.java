package com.springintegration.dsldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import com.springintegration.dsldemo.service.PersonService;

@Configuration
public class PersonIntegrationConfig {

	@Bean
	public MessageChannel findByIdRequest() {
		return MessageChannels.direct("findByIdRequest").get();
	}
	
	
	@Bean
	public MessageChannel findByIdResponse() {
		return MessageChannels.direct("findByIdResponse").get();
	}

	@Bean
	public MessageChannel createRequest() {
		return MessageChannels.direct("createRequest").get();
	}
	
	@Bean
	public IntegrationFlow createIntegrationFlow(PersonService personService) {
		return IntegrationFlows.from("createRequest")
				.handle(personService, "create")
				.get();
	}
}
