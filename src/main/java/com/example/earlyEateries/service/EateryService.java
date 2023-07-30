package com.example.earlyEateries.service;

import java.util.List;

import com.example.earlyEateries.dto.EateryRequestResponse;

public interface EateryService {
	
	EateryRequestResponse create(EateryRequestResponse eatery);
	EateryRequestResponse update(EateryRequestResponse eatery, Long id);
	EateryRequestResponse getById(Long id);
	List<EateryRequestResponse> getAll();
	void delete(Long id);

}
