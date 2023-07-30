package com.example.earlyEateries.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.earlyEateries.Mappers.MappingData;
import com.example.earlyEateries.dto.UserRequestResponse;
import com.example.earlyEateries.entity.User;
import com.example.earlyEateries.exception.ResourceNotFoundException;
import com.example.earlyEateries.repository.UserRepository;
import com.example.earlyEateries.service.UserService;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MappingData mappingData;
	
	
	
	@Override
	public UserRequestResponse create(UserRequestResponse userRequestResponse) {
		
		User user = this.mappingData.dtoToUser(userRequestResponse);		
		User savedUser = this.userRepository.save(user);
		
		return this.mappingData.userToDto(savedUser);
	}

	@Override
	public UserRequestResponse update(UserRequestResponse userRequestResponse, Long id) {
	
	   Optional<User> optionalUser = this.userRepository.findById(id);
		
		if(Objects.isNull(optionalUser)) {
		 new  ResourceNotFoundException("User","id", id);
		}
		 User user =  optionalUser.get();
		 
		user.setEmail(userRequestResponse.getEmail());
		user.setName(userRequestResponse.getName());
		user.setPassword(userRequestResponse.getPassword());
		User updatedUser = this.userRepository.save(user);
		
		return  this.mappingData.userToDto(updatedUser);
	}

	@Override
	public UserRequestResponse getById(Long id) {
	
		Optional<User> optionalUser = this.userRepository.findById(id);
		
		if(Objects.isNull(optionalUser)) {
			 new ResourceNotFoundException("User","id", id);
			}
		User user = optionalUser.get();
		return this.mappingData.userToDto(user);
	}

	@Override
	public List<UserRequestResponse> getAll() {

		List<User> userList = this.userRepository.findAll();
		
		List<UserRequestResponse> userRequestResponsesList = userList.stream().map(user-> this.mappingData.userToDto(user)).collect(Collectors.toList());
		return  userRequestResponsesList;
	}

	@Override
	public void delete(Long id) {
		
        Optional<User> optionalUser = this.userRepository.findById(id);
		
		if(Objects.isNull(optionalUser)) {
			 new  ResourceNotFoundException("User","id", id);
			}
	     this.userRepository.delete(optionalUser.get());

	}
	
	

}
