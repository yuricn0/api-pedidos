package com.ydcns.pedidos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ydcns.pedidos.dto.UserDTO;
import com.ydcns.pedidos.entities.User;
import com.ydcns.pedidos.repositories.UserRepository;
import com.ydcns.pedidos.services.exceptions.DataBaseException;
import com.ydcns.pedidos.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> findAll() {
	    List<User> users = userRepository.findAll();
	    return users.stream().map(UserDTO::new).collect(Collectors.toList()); // Converte para DTO
	}

	public UserDTO findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(user);
	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = new User(userDTO);
		User savedUser = userRepository.save(user);
		return new UserDTO(savedUser);
		
	}

	public void delete(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		try {
			userRepository.delete(user);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	public UserDTO update(Long id, UserDTO obj) {
		try {
			User entity = userRepository.getReferenceById(id);
			updateData(entity, obj);
			return new UserDTO(userRepository.save(entity));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, UserDTO obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
