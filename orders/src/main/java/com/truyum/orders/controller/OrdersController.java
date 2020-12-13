package com.truyum.orders.controller;

import com.truyum.orders.exception.OrderNotFoundException;
import com.truyum.orders.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @GetMapping("/{id}")
    public ResponseEntity<?> makeOrder(@PathVariable(name="id")int id, @RequestHeader(name="Authorization")String token) throws OrderNotFoundException {
        ResponseEntity<?> responseEntity=ordersService.makeOrder(id, token);
        log.debug("{}", responseEntity);
        return responseEntity;
    }
}
