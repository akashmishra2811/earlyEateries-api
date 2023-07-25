package com.example.earlyEateries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.earlyEateries.entity.User;

public interface UserRepository extends JpaRepository<User , Long> {

	
}
