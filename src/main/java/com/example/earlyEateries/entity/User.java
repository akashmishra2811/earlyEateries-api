package com.example.earlyEateries.entity;

 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.earlyEateries.config.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Entity
@Table(name ="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user_name" , nullable =false , length =100)
    private String name;
	
	@Column(name = "email", nullable  =false ,length =150)
    private String email;
	
	@Column(name = "password", nullable  =false ,length =200)
    private String password;
	
	
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Eatery> eateryItems  =  new ArrayList<>();
	
	
	@OneToMany(mappedBy =  "user", cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<>();
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.name()));
	}


	@Override
	public String getUsername() {
	
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}	
	
	
}
