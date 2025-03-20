package com.ydcns.pedidos.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.ydcns.pedidos.entities.Order;
import com.ydcns.pedidos.entities.User;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserDTO {
	
	@Schema(description = "Identificador único do usuário", example = "1")
	private Long id;
	
	@Schema(description = "Nome do usuário", example = "Yuri")
	private String name;
	
	@Schema(description = "Email do usuário", example = "yuri@gmail.com")
	private String email;
	
	@Schema(description = "Telefone do usuário", example = "930254521")
	private String phone;
	
	private List<Order> orders = new ArrayList<>();
	
	public UserDTO() {
		
	}

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.orders = user.getOrders().stream().collect(Collectors.toList());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}
}
