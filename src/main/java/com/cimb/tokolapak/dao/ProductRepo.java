package com.cimb.tokolapak.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cimb.tokolapak.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public Product findByProductName(String productName);
	
	@Query(value="SELECT * FROM product WHERE price<10000",nativeQuery = true)
	public Iterable<Product> findProductByMinPrice();

	@Query(value = "SELECT * FROM Product WHERE price < :maxPrice and product_name like %:productName%", nativeQuery = true)
	public Iterable<Product> findProductByMaxPrice(@Param("maxPrice") double maxPrice, @Param("productName") String namaProduk);

}
