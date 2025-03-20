package com.ydcns.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ydcns.pedidos.entities.Order;
import com.ydcns.pedidos.repositories.OrderRepository;
import com.ydcns.pedidos.services.exceptions.DataBaseException;
import com.ydcns.pedidos.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public void delete(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
			orderRepository.delete(order);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Order update(Long id, Order obj) {
		try {
			Order entity = orderRepository.getReferenceById(id);
			updateData(entity, obj);
			return orderRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Order entity, Order obj) {
		entity.setOrderStatus(obj.getOrderStatus());
		entity.setPayment(obj.getPayment());
	}
}
