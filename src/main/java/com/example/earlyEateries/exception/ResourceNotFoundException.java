package com.example.earlyEateries.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	Long fieldValue;
	
	public ResourceNotFoundException (String resourceName,String fieldName,Long fieldValue) {
		
		super(String.format("%s not found with %s  :%l ",resourceName, fieldName, 1));
		this.resourceName =resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
		
	}

}
