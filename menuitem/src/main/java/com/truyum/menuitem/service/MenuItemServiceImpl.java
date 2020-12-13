package com.truyum.menuitem.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.truyum.menuitem.entity.MenuItem;
import com.truyum.menuitem.exception.InvalidSession;
import com.truyum.menuitem.exception.MenuItemNotFound;
import com.truyum.menuitem.repository.MenuItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MenuItemServiceImpl implements MenuItemService {
	@Autowired
	MenuItemRepository menuItemRepository;

	@Autowired
	FeignService feignService;

	@Override
	public List<MenuItem> getAllItems(String token) throws InvalidSession {
		log.debug(token);
		if (!validSession(token)) {
			log.debug("Session is not valid");
			throw new InvalidSession();
		}
		List<MenuItem> menuItem = menuItemRepository.findAll();
		log.debug("{}" + menuItem);
		return menuItem;
	}

	@Override
	public ResponseEntity<?> getItemById(int id, String token)
			throws InvalidSession, NoSuchElementException, MenuItemNotFound {
		log.debug(String.valueOf(id));
		if (!validSession(token)) {
			log.debug("Session is not valid");
			throw new InvalidSession();
		}
		ResponseEntity<?> menuItem = null;
		try {
			menuItem = new ResponseEntity<>(menuItemRepository.findById(id).get(), HttpStatus.OK);
		} catch (NoSuchElementException e) {
			throw new MenuItemNotFound();
		}
		log.debug("{}", menuItem);
		return menuItem;
	}

	private boolean validSession(String token) {
		log.debug(token);
		boolean isValid = feignService.verifyToken(token);
		log.debug("{}", isValid);
		return isValid;
	}
}
