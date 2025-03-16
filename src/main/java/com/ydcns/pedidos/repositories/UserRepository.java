package com.ydcns.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydcns.pedidos.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
