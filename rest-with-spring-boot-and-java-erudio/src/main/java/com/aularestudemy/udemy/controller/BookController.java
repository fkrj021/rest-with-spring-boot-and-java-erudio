package com.aularestudemy.udemy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aularestudemy.udemy.dto.v1.BookVO;
import com.aularestudemy.udemy.services.BookServices;
import com.aularestudemy.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "EndPoints for Managing Book")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all book", 
    description = "Finds all book",
    tags = {"Book"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = {@Content(
    							mediaType = "application/json",
    							array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
    						)
    				}),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public ResponseEntity<PagedModel<EntityModel<BookVO>>> findAll(
    		@RequestParam(value = "page", defaultValue = "0") Integer page,
    		@RequestParam(value = "size" , defaultValue = "12") Integer size,
    		@RequestParam(value = "direction", defaultValue = "asc")String direction
    		) throws Exception {
    	
    	var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
    	
    	Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
    	
        return ResponseEntity.ok(bookServices.findAll(pageable));
    }

    @GetMapping(value = "/{id}",    		
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Find a book", 
    description = "Find a book",
    tags = {"Book"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = BookVO.class))
    		),
    		@ApiResponse(description = "No Content",responseCode = "204", content = @Content),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public BookVO findById(
            @PathVariable(value = "id") Long id) throws Exception{
        return bookServices.findById(id);
        }
    
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add new book by passing in a JSON, XML or YML", 
    description = "Add new book",
    tags = {"Book"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = BookVO.class))
    		),
    		@ApiResponse(description = "No Content",responseCode = "204", content = @Content),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public BookVO create(@RequestBody BookVO book) throws Exception {
        return bookServices.create(book);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update a book", 
    description = "Update a book",
    tags = {"Book"},
    responses = {
    		@ApiResponse(description = "Updated", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = BookVO.class))
    		),    		
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public BookVO update(@RequestBody BookVO book) throws Exception {
        return bookServices.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a book", 
    description = "Delete a book",
    tags = {"Book"},
    responses = {
    		@ApiResponse(description = "No content", responseCode = "204", content = @Content),    		
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        bookServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    //////////V2/////////////

//    @PostMapping(value = "/v2",
//            produces = {MediaType.APPLICATION_JSON,
//                    MediaType.APPLICATION_XML,
//                    MediaType.APPLICATION_YML},
//            consumes = {MediaType.APPLICATION_JSON,
//                    MediaType.APPLICATION_XML,
//                    MediaType.APPLICATION_YML})
//    public BookVOV2 createV2(@RequestBody BookVOV2 book){
//        return bookServices.createV2(book);
//    }

}





