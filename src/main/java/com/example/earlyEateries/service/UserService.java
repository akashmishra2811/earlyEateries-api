package com.example.earlyEateries.service;

import java.util.List;

import com.example.earlyEateries.dto.UserRequestResponse;

public interface UserService {

	UserRequestResponse create(UserRequestResponse user);
	UserRequestResponse update(UserRequestResponse user, Long id);
	UserRequestResponse getById(Long id);
	List<UserRequestResponse> getAll();
	void delete(Long id);
	
}
