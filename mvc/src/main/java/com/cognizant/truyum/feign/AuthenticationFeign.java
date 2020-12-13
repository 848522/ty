package com.cognizant.truyum.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.truyum.model.UserLoginCredential;
import com.cognizant.truyum.model.UserToken;


@FeignClient(name = "authentication-service",url = "${feign.client.authapp}")
public interface AuthenticationFeign {

	@PostMapping(value="/login")
	public UserToken login(@RequestBody UserLoginCredential userlogincredentials);
	
	@GetMapping(value="/validate")
	public boolean verifyToken(@RequestHeader(name = "Authorization", required = true)String token);
}