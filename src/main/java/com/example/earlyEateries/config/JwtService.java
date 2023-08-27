package com.example.earlyEateries.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	final static String SECRET_KEY ="vJ3bjyznk/yUADfV36TG0WuRBrROxZeRhP3Mz4H1UqgdTJzHZFLhtvyk0IOR38Co";
	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = extractAllClaims(token);
		
		return  claimsResolver.apply(claims);
		
	}
	
	private Claims extractAllClaims (String token) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
		
	}
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return  Keys.hmacShaKeyFor(keyBytes);
	}
	
	
	public  String generateToken(Map<String, Object> extraClaims , UserDetails userDetails) {
		
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date (System.currentTimeMillis() +1000*24*60))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateToken( UserDetails userDetails) {
		
		return generateToken(new HashMap<>(), userDetails);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
   
		final String username = extractUserName(token);	
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
			
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
