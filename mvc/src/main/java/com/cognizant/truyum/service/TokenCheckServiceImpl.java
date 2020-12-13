package com.cognizant.truyum.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cognizant.truyum.feign.AuthenticationFeign;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenCheckServiceImpl implements TokenCheckService {
	@Autowired
	private AuthenticationFeign authfeign;

	@Override
	public ModelAndView checkStatus(HttpServletRequest request) {
		log.debug("{}", request.getSession().getAttribute("token"));
		if (request.getSession().getAttribute("token") == null) {
			log.debug("User not logged in. Redirecting to login page.");
			return new ModelAndView(new RedirectView("/login"));
		}
		String token = request.getSession().getAttribute("token").toString();
		log.debug("{}", authfeign.verifyToken(token));
		log.debug("{}", token);
		if (!authfeign.verifyToken(token)) {
			log.debug("Token Invalid/expired. Redirecting to login page.");
			return new ModelAndView(new RedirectView("/session-expired"));
		}
		return null;
	}
}