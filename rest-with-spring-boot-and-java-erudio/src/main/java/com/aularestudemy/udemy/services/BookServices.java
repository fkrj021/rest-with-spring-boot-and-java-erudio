package com.aularestudemy.udemy.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aularestudemy.udemy.controller.BookController;
import com.aularestudemy.udemy.dto.v1.BookVO;
import com.aularestudemy.udemy.exceptions.RequiredObjectsIsNullException;
import com.aularestudemy.udemy.exceptions.ResourceNotFoundException;
import com.aularestudemy.udemy.mapper.DozerMapper;
import com.aularestudemy.udemy.model.Book;
import com.aularestudemy.udemy.repository.BookRepository;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

//    @Autowired
//    BookMapper bookMapper;

    public List<BookVO> findAll() throws Exception {

        logger.info("Find All book!");
        var books = DozerMapper.parseListObjects(repository.findAll(),BookVO.class) ;
        for (BookVO p : books) {
            p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
        }
        return books;
    }
    public BookVO findById(Long id) throws Exception {
        logger.info("Finding one BookDTO!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo =  DozerMapper.parseObject(entity,BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO bookVO) throws Exception {
    	
    	if(bookVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Create one BookDTO!");
        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one Book!: " + id);
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }

    public BookVO update(BookVO bookVO) throws Exception {
    	
    	if(bookVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Update one BookDTO!");
        var entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        var vo = DozerMapper.parseObject(repository.save(entity),BookVO.class) ;
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
        //// v2
//    public BookVOV2 createV2(BookVOV2 bookVO){
//        logger.info("Create one BookDTO V2!");
//        var entity = bookMapper.convertVoToEntity(bookVO);
//        var vo = bookMapper.convertEntityToVo(repository.save(entity)) ;
//        return vo;
//    }
}
