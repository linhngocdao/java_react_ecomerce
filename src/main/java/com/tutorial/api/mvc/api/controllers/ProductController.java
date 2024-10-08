package com.tutorial.api.mvc.api.controllers;

import com.tutorial.api.mvc.api.models.Product;
import com.tutorial.api.mvc.api.models.ResponseObject;
import com.tutorial.api.mvc.api.repositories.ProductRepositories;
import com.tutorial.api.mvc.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

@RestController  // khai báo đây là một annotation để xây dựng các API RESTful
@RequestMapping("/api/v1/products")  // khai báo đường dẫn URL cho API
public class ProductController {

    //DI = Dependency Injection
    @Autowired
    private ProductRepositories ProductRepositories;
    private ProductService productService;

    //GET ALL PRODUCTS
    // khai báo phương thức GET
    @GetMapping("")
    //this is request : http://localhost:8080/api/v1/products/getAll
    List<Product> getAllProducts() {
        return productService.getAllUser();
    }

    //GET PRODUCT BY ID
    @GetMapping("/{id}")
    //this is request : http://localhost:8080/api/v1/products/${id}
    ResponseEntity<ResponseObject> findProductById(@PathVariable Long id) {
        Optional<Product> foundProduct = ProductRepositories.findById(id); //kết quả trả về
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("Success", "Product get successfully", foundProduct)
                ) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Error", "Product not found", null)
        );
    }


    //CREATE PRODUCT
    // khai báo phương thức POST
    //this is request : http://localhost:8080/api/v1/products/create
    @PostMapping("/create")
    ResponseEntity<ResponseObject> createProduct(@RequestBody Product newProduct) {
        //kiểm tra 2 sản phẩm trùng tên nhau
        List<Product> foundProduct = ProductRepositories.findByName(newProduct.getName().trim());
        if (!foundProduct.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Error", "Product already exists", null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Product created successfully", ProductRepositories.save(newProduct))
        );
    }


    //UPDATE PRODUCT
    @PutMapping("/update/{id}")
    //this is request : http://localhost:8080/api/v1/products/update/${id}
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product ProductUpdate = ProductRepositories.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setDescription(newProduct.getDescription());
                    product.setStatus(newProduct.getStatus());
                    return ProductRepositories.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return ProductRepositories.save(newProduct);
                });
        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Product updated successfully", ProductUpdate)
        );
    }

    //DELETE PRODUCT
    @DeleteMapping("/delete/{id}")
    //this is request : http://localhost:8080/api/v1/products/delete/${id}
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exits = ProductRepositories.existsById(id);
        if(exits){
            ProductRepositories.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Delete successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Error", "Product not found", null)
        );
    }

}
