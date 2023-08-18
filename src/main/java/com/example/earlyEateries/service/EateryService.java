package com.example.earlyEateries.service;



import com.example.earlyEateries.dto.EateryPaginationResponse;
import com.example.earlyEateries.dto.EateryRequestResponse;

public interface EateryService {
	
	EateryRequestResponse create(EateryRequestResponse eatery);
	EateryRequestResponse update(EateryRequestResponse eatery, Long id);
	EateryRequestResponse getById(Long id);
	EateryPaginationResponse getAll(Integer pageNumber ,Integer pageSize,String sortBy);
	void delete(Long id);

}
