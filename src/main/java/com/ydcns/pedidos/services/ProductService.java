package com.ydcns.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ydcns.pedidos.entities.Product;
import com.ydcns.pedidos.repositories.ProductRepository;
import com.ydcns.pedidos.services.exceptions.DataBaseException;
import com.ydcns.pedidos.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public void delete(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
			productRepository.delete(product);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Product update(Long id, Product obj) {
		try {
			Product entity = productRepository.getReferenceById(id);
			updateData(entity, obj);
			return productRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product entity, Product obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
	}
}
