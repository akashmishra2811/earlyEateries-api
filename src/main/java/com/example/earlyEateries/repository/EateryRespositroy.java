package com.example.earlyEateries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.earlyEateries.entity.Eatery;
import com.example.earlyEateries.entity.User;



public interface EateryRespositroy  extends JpaRepository<Eatery , Long> {
	
	
	List<Eatery> findByUser(User user);

}
