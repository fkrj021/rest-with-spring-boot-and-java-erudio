package com.aularestudemy.udemy.repository;

import com.aularestudemy.udemy.model.Book;
import com.aularestudemy.udemy.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
