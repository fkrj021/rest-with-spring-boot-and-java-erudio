package com.aularestudemy.udemy.services;

import com.aularestudemy.udemy.controller.PersonController;
import com.aularestudemy.udemy.dto.v1.PersonVO;

import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import com.aularestudemy.udemy.mapper.DozerMapper;
import com.aularestudemy.udemy.mapper.custom.PersonMapper;
import com.aularestudemy.udemy.model.Person;
import com.aularestudemy.udemy.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public List<PersonVO> findAll() throws Exception {

        logger.info("Find All People!");
        var persons = DozerMapper.parseListObjects(repository.findAll(),PersonVO.class) ;
        for (PersonVO p : persons) {
            p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
        }
        return persons;
    }
    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one PersonDTO!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo =  DozerMapper.parseObject(entity,PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO personVO) throws Exception {
    	
    	if(personVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Create one PersonDTO!");
        var entity = DozerMapper.parseObject(personVO, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one Person!: " + id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public PersonVO update(PersonVO personVO) throws Exception {
    	
    	if(personVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Update one PersonDTO!");
        var entity = repository.findById(personVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAdress(personVO.getAdress());
        entity.setGender(personVO.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class) ;
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
        //// v2
    public PersonVOV2 createV2(PersonVOV2 personVO){
        logger.info("Create one PersonDTO V2!");
        var entity = personMapper.convertVoToEntity(personVO);
        var vo = personMapper.convertEntityToVo(repository.save(entity)) ;
        return vo;
    }
}
