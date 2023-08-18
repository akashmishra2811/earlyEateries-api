package com.example.earlyEateries.service.impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.earlyEateries.Mappers.MappingData;
import com.example.earlyEateries.dto.EateryPaginationResponse;
import com.example.earlyEateries.dto.EateryRequestResponse;
import com.example.earlyEateries.entity.Eatery;
import com.example.earlyEateries.entity.User;
import com.example.earlyEateries.exception.ResourceNotFoundException;
import com.example.earlyEateries.repository.EateryRespositroy;
import com.example.earlyEateries.repository.UserRepository;
import com.example.earlyEateries.service.EateryService;

import jakarta.validation.constraints.AssertTrue;


@Service
public class EateryServiceImpl implements EateryService {
	
	
	@Autowired
	private EateryRespositroy eateryRespositroy;
	
	@Autowired
	private MappingData mappingData;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public EateryRequestResponse create(EateryRequestResponse eateryRequestResponse) {
		
		final Long userId = eateryRequestResponse.getUserId();
		 User user  = userRepository.findById(userId).get();
		 
		 if(Objects.isNull(user)) {
			 new  ResourceNotFoundException("User","id", userId);
			}
		 
		  Eatery eatery = this.mappingData.dtoToEatery(eateryRequestResponse);		
		  Eatery savedEatery = this.eateryRespositroy.save(eatery);
			
		  return this.mappingData.eateryToDto(savedEatery);
		
		
	}

	@Override
	public EateryRequestResponse update(EateryRequestResponse eateryRequestResponse, Long eateryId) {
		
		
		final Long userId = eateryRequestResponse.getUserId();
		 User user  = userRepository.findById(userId).get();
		 
		 
		 if(Objects.isNull(user)) {
			 new  ResourceNotFoundException("User","id", userId);
			}
		
		 Eatery eatery = eateryRespositroy.findById(eateryId).get();
	 
		 
		 if(Objects.isNull(eatery)) {
			 new  ResourceNotFoundException("Eatery","id", eateryId);
			}
	
		 final Long savedUserId = eatery.getUser().getId();
		 long value1 =savedUserId.longValue();
		 long value2 =userId.longValue();
		 //need to look in this  pending
		 if(Long.compare(value1, value2)  !=0) {
			 new  ResourceNotFoundException("Eatery","id", eateryId);
		 }
		 
	
		 
		  eatery.setEateryName(eateryRequestResponse.getEateryName());
		  eatery.setPostDate(eateryRequestResponse.getPostDate());
		  eatery.setPrice(eateryRequestResponse.getPrice());
          eatery.setAddress(eateryRequestResponse.getAddress());
          eatery.setDishName(eateryRequestResponse.getDishName());
          eatery.setImageLink(eateryRequestResponse.getImageLink());
          eatery.setWebsiteLink(eateryRequestResponse.getWebsiteLink());
		  Eatery savedEatery = this.eateryRespositroy.save(eatery);
			
		  return this.mappingData.eateryToDto(savedEatery);
	}

	
	
	@Override
	public EateryRequestResponse getById(Long eateryId) {
	
		
		Eatery eatery = eateryRespositroy.findById(eateryId).get();
		 
		 
		 if(Objects.isNull(eatery)) {
			 new  ResourceNotFoundException("Eatery","id", eateryId);
			}
		
		 
		 return this.mappingData.eateryToDto(eatery);
	}

	@Override
	public EateryPaginationResponse getAll(Integer pageNumber , Integer pageSize ,String sortBy) {
		 
		
		
	  PageRequest p =  PageRequest.of(pageNumber, pageSize,Sort.by(sortBy));
 
     Page<Eatery> eateryPage =  this.eateryRespositroy.findAll(p);
     List<Eatery> eateryList =eateryPage.getContent();
		
		List<EateryRequestResponse> eateryRequestResponsesList = eateryList.stream().map(eatery-> this.mappingData.eateryToDto(eatery)).collect(Collectors.toList());
		
		EateryPaginationResponse eateryPaginationResponse = new EateryPaginationResponse();
		eateryPaginationResponse.setContent(eateryRequestResponsesList);
		eateryPaginationResponse.setPageNumber(eateryPage.getNumber());
		eateryPaginationResponse.setPageSize(eateryPage.getSize());
		eateryPaginationResponse.setTotalElements(eateryPage.getTotalElements());
		eateryPaginationResponse.setTotalPages(eateryPage.getTotalPages());
		eateryPaginationResponse.setLastPage(eateryPage.isLast());
		
		return  eateryPaginationResponse;

	}

	@Override
	public void delete(Long id) {
		
		  Optional<Eatery> optionalEatery = this.eateryRespositroy.findById(id);
			
			if(Objects.isNull(optionalEatery)) {
				 new  ResourceNotFoundException("User","id", id);
				}
		     this.eateryRespositroy.delete(optionalEatery.get());

	}

}
