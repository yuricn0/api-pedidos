package com.ydcns.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydcns.pedidos.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
