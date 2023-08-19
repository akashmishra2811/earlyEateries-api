package com.example.earlyEateries.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.earlyEateries.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment , Long>  {

}
