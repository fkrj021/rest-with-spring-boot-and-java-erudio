package com.aularestudemy.udemy.services;

import com.aularestudemy.udemy.dto.v1.PersonVO;

import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import com.aularestudemy.udemy.mapper.DozerMapper;
import com.aularestudemy.udemy.mapper.custom.PersonMapper;
import com.aularestudemy.udemy.model.Person;
import com.aularestudemy.udemy.repository.PersonRepository;
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

    @Autowired
    PersonMapper personMapper;

    public List<PersonVO> findAll(){

        logger.info("Find All People!");

        return DozerMapper.parseListObjects(repository.findAll(),PersonVO.class) ;
    }
    public PersonVO findById(Long id){
        logger.info("Finding one PersonDTO!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity,PersonVO.class);
    }

    public PersonVO create(PersonVO personVO){
        logger.info("Create one PersonDTO!");
        var entity = DozerMapper.parseObject(personVO, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class) ;
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one Person!: " + id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public PersonVO update(PersonVO PersonVO){
        logger.info("Update one PersonDTO!");
        var entity = repository.findById(PersonVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(PersonVO.getFirstName());
        entity.setLastName(PersonVO.getLastName());
        entity.setAdress(PersonVO.getAdress());
        entity.setGender(PersonVO.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class) ;

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
