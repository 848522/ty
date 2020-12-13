package com.truyum.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order Id is invalid.")
public class OrderNotFoundException extends Exception {
}
