package com.truyum.menuitem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Menu Item id is invalid")
public class MenuItemNotFound extends Exception{
}
