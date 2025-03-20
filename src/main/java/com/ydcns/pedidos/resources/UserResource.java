package com.ydcns.pedidos.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ydcns.pedidos.dto.UserDTO;
import com.ydcns.pedidos.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("users")
public class UserResource {

	@Autowired
	private UserService userService;
	

	@Operation(summary = "Lista todos os usuários.")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = userService.findAll();
		return ResponseEntity.ok().body(users);
	}
	

	@Operation(summary = "Retorna um usuário por ID.")
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@Operation(summary = "Cria um novo usuário.")
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
		user = userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	

	@Operation(summary = "Apaga um usuário por ID.")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}


	@Operation(summary = "Atualiza um usuário por ID.")
	@PutMapping("{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
		obj = userService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
