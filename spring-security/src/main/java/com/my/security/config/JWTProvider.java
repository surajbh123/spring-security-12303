package com.my.security.config;


import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static java.util.Date.from;

import java.security.PrivateKey;

@Service
public class JWTProvider {

	public String generateToken(Authentication authentication) {
		User pricipalUser = (User) authentication.getPrincipal();
		System.out.println("generateToken");
		return Jwts.builder()
				.setSubject(pricipalUser.getUsername())
				.claim("Authorities", authentication.getAuthorities())
				.setIssuedAt(Date.from(Instant.now()))
				.signWith(getKey())
				.setExpiration(Date.from(getExpirationDate()))
				.compact(); 
	}
	
	public SecretKey getKey() {
		String key = "zxcvbnmzxcvbnmzxcvbnmzxcvbnmzxcvbnmzxcvbnm";
		return Keys.hmacShaKeyFor(key.getBytes());
	}
	
	public boolean validateToken(String jwt) {
		Jwts.parser().setSigningKey(getKey()).parseClaimsJws(jwt);
		return true;
	}
	
	public String getUsernameFromJwt(String jwt) {
		Claims claims = Jwts.parser()
				.setSigningKey(getKey())
				.parseClaimsJws(jwt)
				.getBody();
		return claims.getSubject();
	}

	public String generateTokenWithUsername(String username) {

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(Date.from(Instant.now()))
				.signWith(getKey())
				.setExpiration(Date.from(getExpirationDate()))
				.compact();
	}
	
	public Instant getExpirationDate() {
		return Instant.now().plusSeconds(6500);
	}
}
