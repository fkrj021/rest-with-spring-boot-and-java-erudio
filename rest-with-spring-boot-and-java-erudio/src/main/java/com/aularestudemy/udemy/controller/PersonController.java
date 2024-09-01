package com.aularestudemy.udemy.controller;

import com.aularestudemy.udemy.dto.v1.PersonVO;
import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(value="/v1",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personServices.findAll();
    }

    @GetMapping(value = "/v1/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById(
            @PathVariable(value = "id") Long id) throws Exception{
        return personServices.findById(id);
        }
    @PostMapping(value="/v1",produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person){
        return personServices.create(person);
    }

    @PutMapping(value="/v1",produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person){
        return personServices.update(person);
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    //////////V2/////////////

    @PostMapping(value = "/v2",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
        return personServices.createV2(person);
    }

}





