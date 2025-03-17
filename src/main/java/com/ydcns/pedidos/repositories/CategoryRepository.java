package com.ydcns.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ydcns.pedidos.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
