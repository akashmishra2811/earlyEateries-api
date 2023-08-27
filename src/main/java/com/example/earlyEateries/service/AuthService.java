package com.example.earlyEateries.service;

import com.example.earlyEateries.dto.AuthenticationResponse;
import com.example.earlyEateries.dto.UserRequestResponse;

public interface AuthService {

	AuthenticationResponse authenticate(UserRequestResponse userRequestResponse);
	
	AuthenticationResponse create(UserRequestResponse userRequestResponse);
}
