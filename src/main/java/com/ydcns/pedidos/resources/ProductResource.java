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

import com.ydcns.pedidos.entities.Product;
import com.ydcns.pedidos.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("products")
public class ProductResource {

	@Autowired
	private ProductService productService;
	

	@Operation(summary = "Lista todos os produtos.")
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> users = productService.findAll();
		return ResponseEntity.ok().body(users);
	}
	

	@Operation(summary = "Retorna um produto por ID.")
	@GetMapping("{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = productService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

	@Operation(summary = "Cria um novo produto.")
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		product = productService.createProduct(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}
	

	@Operation(summary = "Apaga um produto por ID.")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	@Operation(summary = "Atualiza um produto por ID.")
	@PutMapping("{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product obj) {
		obj = productService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
