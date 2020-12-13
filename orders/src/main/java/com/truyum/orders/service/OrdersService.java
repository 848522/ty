package com.truyum.orders.service;

import com.truyum.orders.exception.OrderNotFoundException;
import org.springframework.http.ResponseEntity;

public interface OrdersService  {
    public ResponseEntity<?> makeOrder(int id, String token) throws OrderNotFoundException;
}
