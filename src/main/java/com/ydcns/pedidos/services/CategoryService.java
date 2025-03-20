package com.ydcns.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ydcns.pedidos.entities.Category;
import com.ydcns.pedidos.repositories.CategoryRepository;
import com.ydcns.pedidos.services.exceptions.DataBaseException;
import com.ydcns.pedidos.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	public void delete(Long id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
			categoryRepository.delete(category);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public Category update(Long id, Category obj) {
		try {
			Category entity = categoryRepository.getReferenceById(id);
			updateData(entity, obj);
			return categoryRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
	}
}
