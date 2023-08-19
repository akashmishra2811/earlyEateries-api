package com.example.earlyEateries.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentRequestResponse {

	
    private Long commentId;	
    private String comment;
    private Long   userId;
    private Long  eateryId;

}
