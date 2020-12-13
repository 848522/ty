package com.truyum.menuitem.controller;

import java.util.List;

import com.truyum.menuitem.exception.MenuItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truyum.menuitem.entity.MenuItem;
import com.truyum.menuitem.exception.InvalidSession;
import com.truyum.menuitem.service.MenuItemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuItemController {
    @Autowired
    MenuItemService menuItemService;

    @GetMapping("/viewall")
    public List<MenuItem> getAllMenuItems(@RequestHeader(name="Authorization")String token) throws JSONException, InvalidSession {
        log.debug(token);
        List<MenuItem> menuItems=menuItemService.getAllItems(token);
    	log.debug("{}", menuItems);
        return menuItems;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id, @RequestHeader(name="Authorization")String token) throws JSONException, InvalidSession, MenuItemNotFound {
        log.debug(token);
        log.debug(String.valueOf(id));
        ResponseEntity<?> item= menuItemService.getItemById(id, token);
    	return item;
    }
}
