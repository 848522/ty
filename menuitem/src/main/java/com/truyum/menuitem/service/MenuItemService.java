package com.truyum.menuitem.service;

import com.truyum.menuitem.entity.MenuItem;
import com.truyum.menuitem.exception.InvalidSession;
import com.truyum.menuitem.exception.MenuItemNotFound;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

public interface MenuItemService {
    public List<MenuItem> getAllItems(String token) throws InvalidSession, JSONException;

    public ResponseEntity<?> getItemById(int id, String token) throws InvalidSession, JSONException, NoSuchElementException, MenuItemNotFound;
}
