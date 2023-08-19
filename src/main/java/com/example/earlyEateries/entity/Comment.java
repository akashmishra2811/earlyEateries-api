package com.example.earlyEateries.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  commentId;
	
	@Column(name = "comment" )
	private String comment;
	
	@ManyToOne
	private Eatery eatery ;
	
	
	@ManyToOne
	private User user;
	
	
	

}
