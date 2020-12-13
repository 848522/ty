package com.truyum.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truyum.authentication.exception.CredentialsException;
import com.truyum.authentication.model.UserLoginCredential;
import com.truyum.authentication.model.UserToken;
import com.truyum.authentication.service.JwtUtil;
import com.truyum.authentication.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	JwtUtil jwtutil;
	@Autowired
	UserService userservice;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserLoginCredential userlogincredentials) throws CredentialsException{
		final UserDetails userdetails = userservice.loadUserByUsername(userlogincredentials.getUsername());
		log.debug("{}", userdetails);
		if (!userdetails.getPassword().equals(userlogincredentials.getPassword())) {
			throw new CredentialsException();
		}
		return new ResponseEntity<>(
				new UserToken(userlogincredentials.getUsername(), jwtutil.generateToken(userdetails)), HttpStatus.OK);
	}

	@GetMapping(value = "/validate")
	public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token1) {
		String token = token1.substring(7);
		boolean ans = false;
		log.debug("{}", token);
		try {
			if (jwtutil.validateToken(token))
				ans = true;
		} catch (ExpiredJwtException e) {
			ans = false;
		}
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}
}