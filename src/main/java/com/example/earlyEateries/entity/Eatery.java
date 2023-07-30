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
@Table(name ="eateries")
@NoArgsConstructor
@Getter
@Setter
public class Eatery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eateryId;
	
	@Column(name = "eatery_name" , nullable =false , length =100)
    private String eateryName;
	
	@Column(name = "postDate", nullable  =false ,length =50)
    private String postDate;
	
	@Column(name = "price", nullable  =false)
    private Long price;
	
	@Column(name = "address" , nullable =false , length =300)
    private String address;
	
	@Column(name = "website_link", nullable  =false ,length =150)
    private String websiteLink;
	
	@Column(name = "image_link", nullable  =false ,length =150)
    private String  imageLink;
	
	@Column(name = "dishName", nullable  =false ,length =200)
    private String  dishName;
	
	@ManyToOne()
	private User user;
	
	

}
