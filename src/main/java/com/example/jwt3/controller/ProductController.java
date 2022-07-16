package com.example.jwt3.controller;

import com.example.jwt3.entity.Product;
import com.example.jwt3.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    @RolesAllowed({"ROLE_CUSTOMER","ROLE_EDITOR","ROLE_ADMIN"})
    public List<Product> list(){
        return productRepository.findAll();
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product){
        Product savedProduct =productRepository.save(product);
        URI productURI = URI.create("/products/"+ savedProduct.getId());

        return ResponseEntity.created(productURI).body(savedProduct);
    }
}
