package com.cimb.tokolapak.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cimb.tokolapak.dao.ProductRepo;
import com.cimb.tokolapak.entity.Product;
import com.cimb.tokolapak.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable() int id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable() int id) {
		return productService.getProductById(id);
	}

	@GetMapping("/productname/{productName}")
	public Product getProductByProductName(@PathVariable() String productName) {
		return productRepo.findByProductName(productName);
	}
	
	@GetMapping("products/minprice")
	public Iterable<Product> getProductByMinPrice(){
		return productRepo.findProductByMinPrice();
	}
	
	
}
