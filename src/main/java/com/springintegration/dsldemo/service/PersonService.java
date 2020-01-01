package com.springintegration.dsldemo.service;

import java.util.List;

import com.springintegration.dsldemo.dto.PersonDto;

public interface PersonService {
	
	List<PersonDto> read();
	
	PersonDto findById(Integer id);

	void create(PersonDto personDto);

}
