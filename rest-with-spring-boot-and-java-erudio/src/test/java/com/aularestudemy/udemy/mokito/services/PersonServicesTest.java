//package com.aularestudemy.udemy.mokito.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestInstance.Lifecycle;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.aularestudemy.udemy.dto.v1.PersonVO;
//import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
//import com.aularestudemy.udemy.model.Person;
//import com.aularestudemy.udemy.repository.PersonRepository;
//import com.aularestudemy.udemy.services.PersonServices;
//import com.aularestudemy.unittests.mapper.mocks.MockPerson;
//
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class PersonServicesTest {
//	
//	MockPerson input;
//	
//	@InjectMocks
//	private PersonServices service;
//	
//	@Mock
//	PersonRepository personRepository; 
//	
//	
//	@BeforeEach
//	void setUpMocks() throws Exception {
//		input = new MockPerson();
//		MockitoAnnotations.openMocks(this);
//		
//	}
//
//	@Test
//	void testFindById() throws Exception {
//		Person entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
//		
//		var result = service.findById(1L);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", result.getAdress());
//		assertEquals("First Name Test1", result.getFirstName());
//		assertEquals("Last Name Test1", result.getLastName());
//		assertEquals("Female", result.getGender());
//	}
//	
//	@Test
//	void testFindAll() throws Exception {
//		List<Person> list = input.mockEntityList();		
//		
//		when(personRepository.findAll()).thenReturn(list);
//		
//		var people = service.findAll();
//		assertNotNull(people);
//		assertEquals(14, people.size());
//		
//		var personOne = people.get(1);
//		assertNotNull(personOne);
//		assertNotNull(personOne.getKey());
//		assertNotNull(personOne.getLinks());		
//		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", personOne.getAdress());
//		assertEquals("First Name Test1", personOne.getFirstName());
//		assertEquals("Last Name Test1", personOne.getLastName());
//		assertEquals("Female", personOne.getGender());
//		
//		var personFour = people.get(4);
//		assertNotNull(personFour);
//		assertNotNull(personFour.getKey());
//		assertNotNull(personFour.getLinks());		
//		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
//		assertEquals("Addres Test4", personFour.getAdress());
//		assertEquals("First Name Test4", personFour.getFirstName());
//		assertEquals("Last Name Test4", personFour.getLastName());
//		assertEquals("Male", personFour.getGender());
//		
//		var personSeven = people.get(7);
//		assertNotNull(personSeven);
//		assertNotNull(personSeven.getKey());
//		assertNotNull(personSeven.getLinks());		
//		assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
//		assertEquals("Addres Test7", personSeven.getAdress());
//		assertEquals("First Name Test7", personSeven.getFirstName());
//		assertEquals("Last Name Test7", personSeven.getLastName());
//		assertEquals("Female", personSeven.getGender());
//	}
//
//	@Test
//	void testCreate() throws Exception {
//		Person entity = input.mockEntity(1);
//		Person persisted = entity;
//		persisted.setId(1L);
//		
//		PersonVO vo = input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(personRepository.save(entity)).thenReturn(persisted);
//		
//		var result = service.create(vo);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", result.getAdress());
//		assertEquals("First Name Test1", result.getFirstName());
//		assertEquals("Last Name Test1", result.getLastName());
//		assertEquals("Female", result.getGender());
//	}
//	@Test
//	void testCreateWithNullPerson() throws Exception {
//		Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
//			service.create(null);
//		});
//		
//		String expectedMessage = "It is not allowed to persist a null object";
//		String actualMessage = exception.getMessage();
//		
//		assertTrue(actualMessage.contains(expectedMessage));
//		
//	}
//	
//	@Test
//	void testUpdateWithNullPerson() throws Exception {
//		Exception exception = assertThrows(RequiredObjectsIsNullException.class, () -> {
//			service.update(null);
//		});
//		
//		String expectedMessage = "It is not allowed to persist a null object";
//		String actualMessage = exception.getMessage();
//		
//		assertTrue(actualMessage.contains(expectedMessage));
//		
//	}
//
//	@Test
//	void testDelete() throws Exception {
//		Person entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
//		service.delete(1L);
//
//	}
//
//	@Test
//	void testUpdate() throws Exception {
//		Person entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		Person persisted = entity;
//		persisted.setId(1L);
//		
//		PersonVO vo = input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
//		when(personRepository.save(entity)).thenReturn(persisted);
//		
//		var result = service.update(vo);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
//		assertEquals("Addres Test1", result.getAdress());
//		assertEquals("First Name Test1", result.getFirstName());
//		assertEquals("Last Name Test1", result.getLastName());
//		assertEquals("Female", result.getGender());
//	}
//
//}
