package com.example.earlyEateries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.earlyEateries.config.ApiConstants;
import com.example.earlyEateries.dto.ApiResponse;
import com.example.earlyEateries.dto.CommentRequestResponse;
import com.example.earlyEateries.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.Comment.END_POINT)
public class CommentController {
 
	@Autowired
	private CommentService commentService;
	
	@PostMapping("")
	public ResponseEntity<CommentRequestResponse> create(@Valid @RequestBody CommentRequestResponse commentRequestResponse){
		
		CommentRequestResponse createdCommentRequestResponse = this.commentService.create(commentRequestResponse);
		
		
		return new ResponseEntity<>(createdCommentRequestResponse,HttpStatus.CREATED);
		
		
	}
	@PutMapping("/{id}")
     public ResponseEntity<CommentRequestResponse> update(@Valid @RequestBody CommentRequestResponse commentRequestResponse, @PathVariable("id") Long id){
		
		CommentRequestResponse updatedCommentRequestResponse = this.commentService.update(commentRequestResponse, id);
		
		return ResponseEntity.ok(updatedCommentRequestResponse) ;
		
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete( @PathVariable("id") Long id){
		
   	     this.commentService.delete(id);
   	    return new ResponseEntity<ApiResponse>( new ApiResponse("The Comment has been deleted Successfully", true),HttpStatus.OK);
    }
	
	
	
}
