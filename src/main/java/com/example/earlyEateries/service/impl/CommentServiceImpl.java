package com.example.earlyEateries.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.earlyEateries.Mappers.MappingData;
import com.example.earlyEateries.dto.CommentRequestResponse;
import com.example.earlyEateries.entity.Comment;
import com.example.earlyEateries.entity.Eatery;
import com.example.earlyEateries.entity.User;
import com.example.earlyEateries.exception.ResourceNotFoundException;
import com.example.earlyEateries.repository.CommentRepository;
import com.example.earlyEateries.repository.EateryRespositroy;
import com.example.earlyEateries.repository.UserRepository;
import com.example.earlyEateries.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository  commentRepository;
	
	@Autowired
	private EateryRespositroy eateryRespositroy;
	
	@Autowired
	private MappingData mappingData;

	@Override
	public CommentRequestResponse create(CommentRequestResponse commentRequestResponse) {

		final Long userId = commentRequestResponse.getUserId();
		final Long eateryId = commentRequestResponse.getEateryId();
		 User user  = userRepository.findById(userId).get();
		 
		 if(Objects.isNull(user)) {
			 new  ResourceNotFoundException("User","id", userId);
			}
		 
		Eatery eatery = eateryRespositroy.findById(eateryId).get();
		
		 if(Objects.isNull(eatery)) {
			 new  ResourceNotFoundException("Eatery","id", eateryId);
			}
		 
		Comment comment = this.mappingData.dtoToComment(commentRequestResponse);
		Comment savedComment = this.commentRepository.save(comment);
		
		return  this.mappingData.commentToDto(savedComment);
	}

	@Override
	public CommentRequestResponse update(CommentRequestResponse commentRequestResponse, Long commentId) {


		final Long userId = commentRequestResponse.getUserId();
		final Long eateryId = commentRequestResponse.getEateryId();
		 User user  = userRepository.findById(userId).get();
		 
		 if(Objects.isNull(user)) {
			 new  ResourceNotFoundException("User","id", userId);
			}
		 
		Eatery eatery = eateryRespositroy.findById(eateryId).get();
		
		 if(Objects.isNull(eatery)) {
			 new  ResourceNotFoundException("Eatery","id", eateryId);
			}
		 
		 Comment  commentToUpdate = commentRepository.findById(commentId).get();
		 
		 if(Objects.isNull(commentToUpdate)) {
			 new  ResourceNotFoundException("Comment","id", eateryId);
			}
		 commentToUpdate.setComment(commentRequestResponse.getComment());
		 
		 
		return this.mappingData.commentToDto(commentToUpdate);
	}

	
	@Override
	public void delete(Long id) {
		Comment comment = commentRepository.findById(id).get();

		 if(Objects.isNull(comment)) {
			 new  ResourceNotFoundException("Comment","id", id);
			}
		 
		 this.commentRepository.delete(comment);
	}

}
