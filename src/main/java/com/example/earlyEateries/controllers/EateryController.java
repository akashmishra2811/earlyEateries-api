package com.example.earlyEateries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.example.earlyEateries.dto.ApiResponse;
import com.example.earlyEateries.dto.EateryRequestResponse;
import com.example.earlyEateries.service.EateryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/eatery")
public class EateryController {
	
	 @Autowired
	private EateryService eateryService;
	
	@PostMapping("")
	public ResponseEntity<EateryRequestResponse> create(@Valid @RequestBody EateryRequestResponse eateryRequestResponse){
		
		EateryRequestResponse createdEateryRequestResponse = this.eateryService.create(eateryRequestResponse);
		
		
		return new ResponseEntity<>(createdEateryRequestResponse,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<EateryRequestResponse> update(@Valid @RequestBody EateryRequestResponse eateryRequestResponse, @PathVariable("id") Long id){
		
		EateryRequestResponse updatedEateryRequestResponse = this.eateryService.update(eateryRequestResponse, id);
		
		return ResponseEntity.ok(updatedEateryRequestResponse) ;
		
		
	}
	
	@GetMapping("")
	public ResponseEntity<List<EateryRequestResponse>> get(@RequestParam(value ="pageNumber", defaultValue ="1") Integer pageNumber,@RequestParam(value ="pageSize", defaultValue ="25") Integer pageSize){
		
		List<EateryRequestResponse> eateryRequestResponseList = this.eateryService.getAll(pageNumber,pageSize);
		
		
		return new ResponseEntity<>(eateryRequestResponseList,HttpStatus.OK);
		
		
	}
	
	  @DeleteMapping("/{id}")
	     public ResponseEntity<ApiResponse> delete( @PathVariable("id") Long id){
	    	  this.eateryService.delete(id);
	    	  
	    	  return new ResponseEntity<ApiResponse>( new ApiResponse("The eatery has been deleted Successfully", true),HttpStatus.OK);
	     }

	

}
