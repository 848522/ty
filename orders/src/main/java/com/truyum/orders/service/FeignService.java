package com.truyum.orders.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "menuitem", url="localhost:8090/menu")
public interface FeignService {
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id, @RequestHeader(name="Authorization")String token);
}
