package com.aularestudemy.udemy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aularestudemy.udemy.dto.v1.security.AccountCredentialVO;
import com.aularestudemy.udemy.dto.v1.security.TokenVO;
import com.aularestudemy.udemy.repository.UserRepository;
import com.aularestudemy.udemy.security.Jwt.JwtTokenProvider;

@Service
public class AuthServices {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity signin(AccountCredentialVO data) {
		try {
			var username = data.getUserName();
			var password = data.getPassword();
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			var user = userRepository.findByUsername(username);
			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());
			}else {
				throw new UsernameNotFoundException("Username " + username + "not found !");
			}
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity refreshToken(String username, String refreshToken) {

			var user = userRepository.findByUsername(username);			
			var tokenResponse = new TokenVO();
			if (user != null) {
				tokenResponse = jwtTokenProvider.refreshToken(refreshToken);
			}else {
				throw new UsernameNotFoundException("Username " + username + "not found !");
			}
			return ResponseEntity.ok(tokenResponse);
		
	}

}
