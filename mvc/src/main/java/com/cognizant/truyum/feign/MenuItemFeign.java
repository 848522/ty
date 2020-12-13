package com.cognizant.truyum.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.truyum.model.MenuItem;

@FeignClient(name = "menuitem-service", url = "${feign.client.menu}")
public interface MenuItemFeign {
	@GetMapping("/viewall")
	public List<MenuItem> getAllMenuItems(@RequestHeader(name = "Authorization") String token);

	@GetMapping("/{id}")
	public MenuItem getById(@PathVariable("id") int id, @RequestHeader(name = "Authorization") String token);
}
