package com.example.earlyEateries.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.earlyEateries.dto.EateryRequestResponse;
import com.example.earlyEateries.dto.UserRequestResponse;
import com.example.earlyEateries.entity.Eatery;
import com.example.earlyEateries.entity.User;

@Service
public class MappingData {

	@Autowired
	private ModelMapper modelMapper;

	public User dtoToUser(UserRequestResponse userRequestResponse) {

		User user = modelMapper.map(userRequestResponse, User.class);
		return user;
	}

	public UserRequestResponse userToDto(User user) {
		UserRequestResponse userRequestResponse = modelMapper.map(user, UserRequestResponse.class);

		return userRequestResponse;
		
	}
	
	 public Eatery dtoToEatery(EateryRequestResponse eateryRequestResponse) {
		 
		 Eatery eatery = modelMapper.map(eateryRequestResponse, Eatery.class);
		 
		 return eatery;
	 }
	 
	 public EateryRequestResponse eateryToDto (Eatery eatery) {
		 
		 EateryRequestResponse eateryRequestResponse = modelMapper.map(eatery, EateryRequestResponse.class);
		 
		 return eateryRequestResponse;
	 }
}
