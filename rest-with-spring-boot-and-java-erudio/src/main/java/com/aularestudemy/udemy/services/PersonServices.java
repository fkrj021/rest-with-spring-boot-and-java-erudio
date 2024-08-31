package com.aularestudemy.udemy.services;

import com.aularestudemy.udemy.repository.PersonRepository;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import com.aularestudemy.udemy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        return repository.findAll();
    }
    public Person findById(Long id){

        logger.info("Finding one person!");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person){
        logger.info("Create one person!");
        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Delete one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public Person update(Person person){
        logger.info("Update one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAdress(person.getAdress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

}
