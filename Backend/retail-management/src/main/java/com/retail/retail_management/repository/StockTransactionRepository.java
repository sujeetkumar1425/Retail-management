package com.retail.retail_management.repository;

import com.retail.retail_management.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository
        extends JpaRepository<StockTransaction, Long> {

    List<StockTransaction> findByProductIdOrderByTransactionDateDesc(Long productId);
}