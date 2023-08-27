package com.example.earlyEateries.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequestResponse {

	
	private long id;
	
	@NotNull
	@NotBlank
	@Size(max = 50 , message = "User Name is too long !!")
    private String name;
	
	@Email(message = "Email is invalid")
	private String email;

	@NotNull
	@NotBlank
	@Size(min= 8 , message = "Password must be atleast 8 charcters long")
    private String password;
	
}
