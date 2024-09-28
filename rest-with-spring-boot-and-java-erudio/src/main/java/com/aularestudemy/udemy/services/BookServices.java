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
    BookRepository bookRepository;
    
    @Autowired
    PagedResourcesAssembler<BookVO> assembler;


    public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) throws Exception {

    	logger.info("Finding all books!");

		var booksPage = bookRepository.findAll(pageable);

		var booksVOs = booksPage.map(p -> DozerMapper.parseObject(p, BookVO.class));
		booksVOs.map(p -> {
			try {
				return p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return p;
		});
		
		Link findAllLink = linkTo(
		          methodOn(BookController.class)
		          	.findAll(pageable.getPageNumber(),
	                         pageable.getPageSize(),
	                         "asc")).withSelfRel();
		
		return assembler.toModel(booksVOs, findAllLink);
    }
    public BookVO findById(Long id) throws Exception {
        logger.info("Finding one BookDTO!");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo =  DozerMapper.parseObject(entity,BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO bookVO) throws Exception {
    	
    	if(bookVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Create one BookDTO!");
        var entity = DozerMapper.parseObject(bookVO, Book.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity),BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        logger.info("Delete one Book!: " + id);
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }

    public BookVO update(BookVO bookVO) throws Exception {
    	
    	if(bookVO == null) throw new RequiredObjectsIsNullException();
    	
        logger.info("Update one BookDTO!");
        var entity = bookRepository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
        var vo = DozerMapper.parseObject(bookRepository.save(entity),BookVO.class) ;
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
