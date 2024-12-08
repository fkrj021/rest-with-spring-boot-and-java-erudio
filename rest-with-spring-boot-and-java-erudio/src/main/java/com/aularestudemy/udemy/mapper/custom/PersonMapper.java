package com.aularestudemy.udemy.mapper.custom;

import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 personVOV2 = new PersonVOV2();
        personVOV2.setId(person.getId());
        personVOV2.setAdress(person.getAdress());
        personVOV2.setBirthday(new Date());
        personVOV2.setFirstName(person.getFirstName());
        personVOV2.setLastName(person.getLastName());
        personVOV2.setGender(person.getGender());
        return personVOV2;
    }

    public Person convertVoToEntity(PersonVOV2 personVOV2){
        Person person = new Person();
        person.setId(personVOV2.getId());
        person.setAdress(personVOV2.getAdress());
        person.setFirstName(personVOV2.getFirstName());
        person.setLastName(personVOV2.getLastName());
        person.setGender(personVOV2.getGender());
        return person;
    }

}
