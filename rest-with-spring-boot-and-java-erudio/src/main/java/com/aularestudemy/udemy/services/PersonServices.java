package com.aularestudemy.udemy.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import com.aularestudemy.udemy.controller.PersonController;
import com.aularestudemy.udemy.dto.v1.PersonVO;
import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import com.aularestudemy.udemy.mapper.DozerMapper;
import com.aularestudemy.udemy.mapper.custom.PersonMapper;
import com.aularestudemy.udemy.model.Person;
import com.aularestudemy.udemy.repository.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;
    
    @Autowired
    PagedResourcesAssembler<PersonVO> assembler;

    public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) throws Exception {

        logger.info("Find All People!");
        
        var personPage = repository.findAll(pageable);
        var personVosPage = personPage.map(p ->  DozerMapper.parseObject(p,PersonVO.class));
        
        personVosPage.map(
        		p -> {
					try {
						return p.add(
								linkTo(methodOn(PersonController.class)
										.findById(p.getKey())).withSelfRel());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return p;
				});
        
        Link link = linkTo(methodOn(PersonController.class)
        		.findAll(pageable.getPageNumber(), 
        				pageable.getPageSize(), 
        				"asc")).withSelfRel();
		return assembler.toModel(personVosPage, link);
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
