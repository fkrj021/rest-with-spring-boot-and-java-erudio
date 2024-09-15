package com.aularestudemy.udemy.controller;
import com.aularestudemy.udemy.dto.v1.BookVO;
import com.aularestudemy.udemy.dto.v1.PersonVO;
import com.aularestudemy.udemy.dto.v2.PersonVOV2;
import com.aularestudemy.udemy.services.PersonServices;
import com.aularestudemy.utils.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "Person", description = "EndPoints for Managing People")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all people", 
    description = "Finds all book",
    tags = {"People"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = {@Content(
    							mediaType = "application/json",
    							array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
    						)
    				}),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public List<PersonVO> findAll() throws Exception {
        return personServices.findAll();
    }
    
    //@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Find a person", 
    description = "Find a person",
    tags = {"People"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = PersonVO.class))
    		),
    		@ApiResponse(description = "No Content",responseCode = "204", content = @Content),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public PersonVO findById(
            @PathVariable(value = "id") Long id) throws Exception{
        return personServices.findById(id);
        }
    
    //@CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
    @PostMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Add new book by passing in a JSON, XML or YML", 
    description = "Add new Person",
    tags = {"People"},
    responses = {
    		@ApiResponse(description = "Sucess", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = PersonVO.class))
    		),
    		@ApiResponse(description = "No Content",responseCode = "204", content = @Content),
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return personServices.create(person);
    }

    @PutMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update a Person", 
    description = "Update a Person",
    tags = {"People"},
    responses = {
    		@ApiResponse(description = "Updated", responseCode = "200", 
    				content = @Content(schema = @Schema(implementation = PersonVO.class))
    		),    		
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a person", 
    description = "Delete a person",
    tags = {"People"},
    responses = {
    		@ApiResponse(description = "No content", responseCode = "204", content = @Content),    		
    		@ApiResponse(description = "Bad Request",responseCode = "400", content = @Content),
    		@ApiResponse(description = "Unathorized",responseCode = "401", content = @Content),
    		@ApiResponse(description = "Not Found",responseCode = "404", content = @Content),
    		@ApiResponse(description = "Internal Error",responseCode = "500", content = @Content)
    		
   		}
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personServices.delete(id);
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
//    public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
//        return personServices.createV2(person);
//    }

}





