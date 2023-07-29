package com.example.earlyEateries.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.earlyEateries.dto.UserRequestResponse;
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
}
