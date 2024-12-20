package com.aularestudemy.unittests.mapper.mocks;

import com.aularestudemy.udemy.dto.v1.BookVO;
import com.aularestudemy.udemy.dto.v1.BookVO;
import com.aularestudemy.udemy.model.Book;
import com.aularestudemy.udemy.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Some Author" + number);
        book.setTitle("Some Title" + number);
        book.setPrice(25D);
        book.setId(number.longValue());
        book.setLaunchDate(new Date());
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Some Author" + number);
        book.setTitle("Some Title" + number);
        book.setPrice(25D);
        book.setKey(number.longValue());
        book.setLaunchDate(new Date());
        return book;
    }

}
