package com.example.earlyEateries.dto;  

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class EateryRequestResponse {
	
	
	private Long eateryId;
	
    private String eateryName;
	
	private String postDate;
	
    private Long price;
	
    private String address;
	
    private String websiteLink;
	
    private String  imageLink;
    
    private String  dishName;
    
    private Long   userId;

}
