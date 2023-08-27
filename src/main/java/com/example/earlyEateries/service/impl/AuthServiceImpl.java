package com.example.earlyEateries.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.earlyEateries.config.JwtService;
import com.example.earlyEateries.config.Role;
import com.example.earlyEateries.dto.AuthenticationResponse;
import com.example.earlyEateries.dto.UserRequestResponse;
import com.example.earlyEateries.entity.User;
import com.example.earlyEateries.repository.UserRepository;
import com.example.earlyEateries.service.AuthService;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private  JwtService  jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public 	AuthenticationResponse create(UserRequestResponse userRequestResponse) {
		 Optional<User> isUserExist = userRepository.findByEmail(userRequestResponse.getEmail());
		 
//		 if(Objects.nonNull(isUserExist)) {
//
//
//
//		 }
		
		   User user =   User.builder().email(userRequestResponse.getEmail())
                .password(passwordEncoder.encode(userRequestResponse.getPassword()))
                .role(Role.USER)
                .name(userRequestResponse.getName())
                .build();

         User savedUser = this.userRepository.save(user);  
		
		 return AuthenticationResponse.builder().token(jwtService.generateToken(savedUser)).build();
	};
	
	@Override
	public AuthenticationResponse authenticate(UserRequestResponse userRequestResponse) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestResponse.getEmail(), userRequestResponse.getPassword()));  
		
		User user = userRepository.findByEmail(userRequestResponse.getEmail()).get();
		
		 return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
	
	}
}
