//package com.aularestudemy.udemy.mokito.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
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
//import com.aularestudemy.udemy.dto.v1.BookVO;
//import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
//import com.aularestudemy.udemy.model.Book;
//import com.aularestudemy.udemy.repository.BookRepository;
//import com.aularestudemy.udemy.services.BookServices;
//import com.aularestudemy.unittests.mapper.mocks.MockBook;
//
//@TestInstance(Lifecycle.PER_CLASS)
//@ExtendWith(MockitoExtension.class)
//class BookServicesTest {
//	
//	MockBook input;
//	
//	@InjectMocks
//	private BookServices service;
//	
//	@Mock
//	private BookRepository bookRepository; 
//	
//	
//	@BeforeEach
//	void setUpMocks() throws Exception {
//		input = new MockBook();
//		MockitoAnnotations.openMocks(this);
//		
//	}
//
//	@Test
//	void testFindById() throws Exception {
//		Book entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
//		
//		var result = service.findById(1L);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
//		assertEquals("Some Author1", result.getAuthor());
//		assertEquals("Some Title1", result.getTitle());
//		assertEquals(25D, result.getPrice());
//		assertNotNull(result.getLaunchDate());
//	}
//	
//	@Test
//	void testFindAll() throws Exception {
//		List<Book> list = input.mockEntityList();		
//		
//		when(bookRepository.findAll()).thenReturn(list);
//		
//		var book = service.findAll();
//		assertNotNull(book);
//		assertEquals(14, book.size());
//		
//		var bookOne = book.get(1);
//		assertNotNull(bookOne);
//		assertNotNull(bookOne.getKey());
//		assertNotNull(bookOne.getLinks());		
//		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
//		assertEquals("Some Author1", bookOne.getAuthor());
//		assertEquals("Some Title1", bookOne.getTitle());
//		assertEquals(25D, bookOne.getPrice());
//		assertNotNull(bookOne.getLaunchDate());
//		
//		var bookFour = book.get(4);
//		assertNotNull(bookFour);
//		assertNotNull(bookFour.getKey());
//		assertNotNull(bookFour.getLinks());		
//		assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
//		assertEquals("Some Author4", bookFour.getAuthor());
//		assertEquals("Some Title4", bookFour.getTitle());
//		assertEquals(25D, bookFour.getPrice());
//		assertNotNull(bookFour.getLaunchDate());
//		
//		var bookSeven = book.get(7);
//		assertNotNull(bookSeven);
//		assertNotNull(bookSeven.getKey());
//		assertNotNull(bookSeven.getLinks());		
//		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
//		assertEquals("Some Author7", bookSeven.getAuthor());
//		assertEquals("Some Title7", bookSeven.getTitle());
//		assertEquals(25D, bookSeven.getPrice());
//		assertNotNull(bookSeven.getLaunchDate());
//	}
//
//	@Test
//	void testCreate() throws Exception {
//		Book entity = input.mockEntity(1);
//		Book persisted = entity;
//		persisted.setId(1L);
//		
//		BookVO vo = input.mockVO(1);
//		vo.setKey(1L);
//		
//		//when(bookRepository.save(any(Book.class))).thenReturn(persisted);
//		when(bookRepository.save(entity)).thenReturn(persisted);
//		
//		var result = service.create(vo);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
//		assertEquals("Some Author1", result.getAuthor());
//		assertEquals("Some Title1", result.getTitle());
//		assertEquals(25D, result.getPrice());
//		assertNotNull(result.getLaunchDate());
//	}
//	@Test
//	void testCreateWithNullBook() throws Exception {
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
//	void testUpdateWithNullBook() throws Exception {
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
//		Book entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
//		service.delete(1L);
//
//	}
//
//	@Test
//	void testUpdate() throws Exception {
//		Book entity = input.mockEntity(1);
//		entity.setId(1L);
//		
//		Book persisted = entity;
//		persisted.setId(1L);
//		
//		BookVO vo = input.mockVO(1);
//		vo.setKey(1L);
//		
//		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
//		when(bookRepository.save(entity)).thenReturn(persisted);
//		
//		var result = service.update(vo);
//		assertNotNull(result);
//		assertNotNull(result.getKey());
//		assertNotNull(result.getLinks());		
//		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
//		assertEquals("Some Author1", result.getAuthor());
//		assertEquals("Some Title1", result.getTitle());
//		assertEquals(25D, result.getPrice());
//		assertNotNull(result.getLaunchDate());
//	}
//
//}
