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

import com.ydcns.pedidos.entities.Order;
import com.ydcns.pedidos.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;
	

	@Operation(summary = "Lista todos os pedidos.")
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> Orders = orderService.findAll();

		return ResponseEntity.ok().body(Orders);
	}
	

	@Operation(summary = "Retorna um pedido por ID.")
	@GetMapping("{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = orderService.findById(id);

		return ResponseEntity.ok().body(obj);
	}
	

	@Operation(summary = "Cria um novo pedido.")
	@PostMapping
	public ResponseEntity<Order> createOrder(Order order) {
		orderService.createOrder(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getId()).toUri();
		return ResponseEntity.created(uri).body(order);
	}
	

	@Operation(summary = "Apaga um pedido por ID.")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Atualiza um pedido por ID.")
	@PutMapping("{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj) {
		obj = orderService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}