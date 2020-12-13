package com.cognizant.truyum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class UserLoginCredential {

	private String username;
	private String password;
}