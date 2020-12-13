package com.cognizant.truyum.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.truyum.feign.MenuItemFeign;
import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.service.TokenCheckService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MenuController {
	@Autowired
	private MenuItemFeign menuItemFeign;
	@Autowired
	private TokenCheckService tokenCheck;

	@GetMapping("/viewall")
	public ModelAndView getMenu(HttpServletRequest request) {
		if (tokenCheck.checkStatus(request) == null) {
			List<MenuItem> menuItems = menuItemFeign
					.getAllMenuItems(request.getSession().getAttribute("token").toString());
			log.debug("{}", menuItems);
			ModelAndView modelAndView = new ModelAndView("menu");
			modelAndView.addObject("menuItemList", menuItems);
			return modelAndView;
		} else
			return tokenCheck.checkStatus(request);
	}

	@GetMapping("/item/{id}")
	public ModelAndView getItem(@PathVariable("id") int id, HttpServletRequest request) {
		if (tokenCheck.checkStatus(request) == null) {
			
			MenuItem menuItem=menuItemFeign.getById(id, request.getSession().getAttribute("token").toString());
	    	log.debug("{}", menuItem);
			ModelAndView modelAndView = new ModelAndView("viewItem");
			modelAndView.addObject("menuItem", menuItem);
			return modelAndView;
		} else
			return tokenCheck.checkStatus(request);
	}
}
