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
import org.springframework.web.bind.annotation.RestController;

import com.example.earlyEateries.dto.ApiResponse;
import com.example.earlyEateries.dto.UserRequestResponse;
import com.example.earlyEateries.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserRequestResponse> create(@Valid @RequestBody UserRequestResponse userRequestResponse){
		
		UserRequestResponse createdRequestResponse = this.userService.create(userRequestResponse);
		
		
		return new ResponseEntity<>(createdRequestResponse,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/{id}")
     public ResponseEntity<UserRequestResponse> update(@Valid @RequestBody UserRequestResponse userRequestResponse, @PathVariable("id") Long id){
		
		UserRequestResponse updatedRequestResponse = this.userService.update(userRequestResponse, id);
		
		return ResponseEntity.ok(updatedRequestResponse) ;
		
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserRequestResponse>> get(){
		
		List<UserRequestResponse> userRequestResponseList = this.userService.getAllUsers();
		
		
		return new ResponseEntity<>(userRequestResponseList,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserRequestResponse> getById(@PathVariable("id") Long id){
		
	   UserRequestResponse userRequestResponse = this.userService.getUserById(id);
		
		
		return new ResponseEntity<>(userRequestResponse,HttpStatus.OK);
		
		
	}
	
	
     @DeleteMapping("/{id}")
     public ResponseEntity<ApiResponse> delete( @PathVariable("id") Long id){
    	  this.userService.delete(id);
    	  
    	  return new ResponseEntity<ApiResponse>( new ApiResponse("The user has been deleted Successfully", true),HttpStatus.OK);
     }


}
