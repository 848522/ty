package com.truyum.menuitem.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth", url = "localhost:8092/auth")
public interface FeignService {
    @GetMapping(value = "/validate")
	public boolean verifyToken(@RequestHeader(name = "Authorization", required = true)String token);
}
