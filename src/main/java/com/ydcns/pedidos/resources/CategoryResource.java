package com.ydcns.pedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydcns.pedidos.entities.Category;
import com.ydcns.pedidos.services.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> users = categoryService.findAll();
		
		return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = categoryService.findById(id);
		
		return ResponseEntity.ok().body(obj); 
	}
}
