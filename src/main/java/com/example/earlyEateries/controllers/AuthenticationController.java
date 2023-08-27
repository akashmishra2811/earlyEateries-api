package com.example.earlyEateries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.earlyEateries.config.ApiConstants;
import com.example.earlyEateries.dto.AuthenticationResponse;
import com.example.earlyEateries.dto.UserRequestResponse;
import com.example.earlyEateries.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.Authentication.END_POINT)
public class AuthenticationController {
	
	@Autowired
	private  AuthService authService;
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> auth( @RequestBody UserRequestResponse userRequestResponse){
		
		return ResponseEntity.ok(authService.authenticate(userRequestResponse));
				
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> create(@Valid @RequestBody UserRequestResponse userRequestResponse){
			
		return ResponseEntity.ok(authService.create(userRequestResponse));
				
	}

}
