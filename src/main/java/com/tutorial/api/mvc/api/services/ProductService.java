package com.tutorial.api.mvc.api.services;

import com.tutorial.api.mvc.api.models.Product;
import com.tutorial.api.mvc.api.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepositories productRepositories;

    public List<Product> getAllUser() {
        return productRepositories.findAll();
    }

    
}
