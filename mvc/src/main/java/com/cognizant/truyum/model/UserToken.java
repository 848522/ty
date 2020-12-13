package com.cognizant.truyum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class UserToken {
	private String username;
	private String authToken;
}