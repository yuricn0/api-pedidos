package com.ydcns.pedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydcns.pedidos.entities.Order;
import com.ydcns.pedidos.services.OrderService;

@RestController
@RequestMapping("orders")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> Orders = orderService.findAll();
		
		return ResponseEntity.ok().body(Orders);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = orderService.findById(id);
		
		return ResponseEntity.ok().body(obj); 
	}
}
