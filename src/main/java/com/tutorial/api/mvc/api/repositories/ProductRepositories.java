package com.tutorial.api.mvc.api.repositories;

import com.tutorial.api.mvc.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// la nơi chứa data
public interface  ProductRepositories extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
