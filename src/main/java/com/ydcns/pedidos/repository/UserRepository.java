package com.ydcns.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ydcns.pedidos.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
