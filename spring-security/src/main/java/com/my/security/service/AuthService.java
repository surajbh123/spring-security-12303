package com.my.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.security.config.JWTProvider;
import com.my.security.dto.AuthenticationResponse;
import com.my.security.dto.LoginRequest;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final JWTProvider jwtProvider;
	
	public AuthenticationResponse login(LoginRequest loginRequest) {
		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
									loginRequest.getUsername(), 
									loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.jwtProvider.generateToken(authentication);
		
		return AuthenticationResponse.builder()
						.authenticationToken(token)
						//.refreshToken(this.refreshTokenService.generateRefreshToken().getToken())
						.expiresAt(this.jwtProvider.getExpirationDate())
						.username(loginRequest.getUsername()).build();
	}
}
