package com.example.earlyEateries.entity;

 
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="users")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user_name" , nullable =false , length =100)
    private String name;
	
	@Column(name = "email", nullable  =false ,length =150)
    private String email;
	
	@Column(name = "password", nullable  =false ,length =200)
    private String password;
	
}
