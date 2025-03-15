package com.ydcns.pedidos.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydcns.pedidos.entities.User;

@RestController
@RequestMapping("users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		User u = new User(1L, "Pedro", "pedro@email", "9999", "12345");
		return ResponseEntity.ok().body(u);
	}

}
