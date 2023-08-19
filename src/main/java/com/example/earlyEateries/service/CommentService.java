package com.example.earlyEateries.service;

import com.example.earlyEateries.dto.CommentRequestResponse;

public interface CommentService {
	
	CommentRequestResponse  create(CommentRequestResponse comment);
	CommentRequestResponse update(CommentRequestResponse comment, Long id);
	void delete(Long id);

}
