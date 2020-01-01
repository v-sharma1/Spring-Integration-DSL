package com.springintegration.dsldemo.repository;

import org.springframework.data.repository.CrudRepository;

import com.springintegration.dsldemo.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
