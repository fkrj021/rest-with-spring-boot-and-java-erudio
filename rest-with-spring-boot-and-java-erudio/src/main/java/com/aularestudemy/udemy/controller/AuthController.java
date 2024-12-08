package com.aularestudemy.udemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aularestudemy.udemy.dto.v1.security.AccountCredentialVO;
import com.aularestudemy.udemy.services.AuthServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication EndPoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

	
	@Autowired
	AuthServices authServices;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialVO data) {
		if (checkIsParamIsNotNull(data)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request!");
		var token = authServices.signin(data);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request!");
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh toklen for authenticated user and returns a token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if (checkIfParamsIsNotNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request!");
		var token = authServices.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid cliente request!");
		return token;
	}

	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank()||
				username == null || username.isBlank();
	}

	private boolean checkIsParamIsNotNull(AccountCredentialVO data) {
		return data == null || data.getUserName() == null || data.getUserName().isBlank()
				|| data.getPassword() == null || data.getPassword().isBlank();
	}
	
}
