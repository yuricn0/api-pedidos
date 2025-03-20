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

import com.ydcns.pedidos.entities.Category;
import com.ydcns.pedidos.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("categories")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;
	
	@Operation(summary = "Lista todas as categorias.")
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> users = categoryService.findAll();

		return ResponseEntity.ok().body(users);
	}
	

	@Operation(summary = "Retorna uma categoria pelo ID.")
	@GetMapping("{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = categoryService.findById(id);

		return ResponseEntity.ok().body(obj);
	}
	

	@Operation(summary = "Cria uma nova categoria.")
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		category = categoryService.createCategory(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}
	

	@Operation(summary = "Apaga uma categoria por ID.")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	@Operation(summary = "Atualiza uma categoria por ID.")
	@PutMapping("{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj) {
		obj = categoryService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
