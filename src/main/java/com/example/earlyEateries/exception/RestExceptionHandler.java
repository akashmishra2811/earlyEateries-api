package com.example.earlyEateries.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.aot.PublicMethodReflectiveProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.earlyEateries.dto.ApiResponse;

import net.bytebuddy.asm.Advice.Return;

@RestControllerAdvice
public class RestExceptionHandler {

	  @ExceptionHandler(ResourceNotFoundException.class)
	 public ResponseEntity<ApiResponse>  resourceNotFoundExceptionHandler(ResourceNotFoundException err){
		  String errMessage = err.getMessage();
		ApiResponse apiResponse = new ApiResponse(errMessage,false);  
		  return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	 }

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public  ResponseEntity<Map<String,String>> handleMethodArgumentNotValidation(MethodArgumentNotValidException err){
		 
		 Map<String,String> response = new HashMap<>();
		 
		 err.getBindingResult().getAllErrors().forEach((error)->{
			 final String fieldName = ((FieldError)error).getField();
			 final String  errorMessage = error.getDefaultMessage();
			 response.put(fieldName, errorMessage);
		 });
		 
		 return  new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
		 
	 }
	 
	 
}
