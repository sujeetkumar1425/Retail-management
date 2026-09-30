package com.retail.retail_management.repository;

import com.retail.retail_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.quantity <= p.reorderLevel")
    List<Product> findLowStockProducts();
}