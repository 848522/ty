package com.cognizant.truyum.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public interface TokenCheckService {
	public ModelAndView checkStatus(HttpServletRequest request);
}