package com.truyum.authentication.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.truyum.authentication.model.UserLoginCredential;
import com.truyum.authentication.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		log.debug(s);
		UserLoginCredential userLoginCredential = userRepository.findById(s).get();
		UserDetails user = new User(userLoginCredential.getUsername(), userLoginCredential.getPassword(),
				new ArrayList<>());
		log.debug("{}", user);
		return user;
	}
}