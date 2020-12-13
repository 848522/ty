package com.cognizant.truyum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cognizant.truyum.feign.AuthenticationFeign;
import com.cognizant.truyum.model.UserLoginCredential;
import com.cognizant.truyum.model.UserToken;
import com.cognizant.truyum.service.TokenCheckService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	private AuthenticationFeign authfeign;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().setAttribute("token", null);
		request.getSession().setAttribute("name", null);
		ModelAndView model = new ModelAndView("login");
		model.addObject("logOutMessage", "You have been logged out successfully.");
		return model;
	}

	@PostMapping("/login")
	public ModelAndView postLogin(@ModelAttribute("credential") UserLoginCredential credential,
			HttpServletRequest request) throws FeignException{
		log.debug("{}", credential);
		UserToken res = null;
		try {
			res = authfeign.login(credential);
		} catch (Exception e) {
			log.debug("Invalid credentials. Redirecting to error page.");
			return new ModelAndView(new RedirectView("loginerror"));
		}
		log.debug("{}", res.getAuthToken());
		request.getSession().setAttribute("token", "Bearer " + res.getAuthToken());
		request.getSession().setAttribute("name", credential.getUsername());
		log.debug("{}", credential);
		ModelAndView model = new ModelAndView(new RedirectView("viewall"));
		return model;
	}

	@GetMapping("/loginerror")
	public ModelAndView loginerror() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("CredentialsError", "Invalid Credentials.");
		return model;
	}

	@GetMapping("/session-expired")
	public ModelAndView sessionExpired() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("SessionExpired", "Session Expired. Please login again.");
		return model;
	}
}