package com.example.earlyEateries.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@Setter
@Getter
public class UserRequestResponse {

	
	private long id;
    private String name;
	private String email;
    private String password;
	
}
