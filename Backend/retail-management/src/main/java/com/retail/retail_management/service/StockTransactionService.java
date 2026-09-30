package com.retail.retail_management.service;

import com.retail.retail_management.entity.Product;
import com.retail.retail_management.entity.StockTransaction;
import com.retail.retail_management.entity.TransactionType;
import com.retail.retail_management.repository.ProductRepository;
import com.retail.retail_management.repository.StockTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockTransactionService {

    private final StockTransactionRepository stockTransactionRepository;
    private final ProductRepository productRepository;

    public StockTransactionService(
            StockTransactionRepository stockTransactionRepository,
            ProductRepository productRepository) {

        this.stockTransactionRepository = stockTransactionRepository;
        this.productRepository = productRepository;
    }

    public StockTransaction addStockTransaction(
            Long productId,
            TransactionType type,
            Integer quantity,
            String description) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        if (quantity == null || quantity <= 0) {
            throw new RuntimeException(
                    "Quantity must be greater than zero");
        }

        if (type == TransactionType.STOCK_IN) {

            product.setQuantity(
                    product.getQuantity() + quantity
            );

        } else if (type == TransactionType.STOCK_OUT) {

            if (product.getQuantity() < quantity) {
                throw new RuntimeException(
                        "Insufficient stock");
            }

            product.setQuantity(
                    product.getQuantity() - quantity
            );
        }

        productRepository.save(product);

        StockTransaction transaction =
                new StockTransaction(
                        product,
                        type,
                        quantity,
                        description
                );

        return stockTransactionRepository.save(transaction);
    }

    public List<StockTransaction> getTransactionsByProduct(
            Long productId) {

        return stockTransactionRepository
                .findByProductIdOrderByTransactionDateDesc(productId);
    }

    public List<StockTransaction> getAllTransactions() {

        return stockTransactionRepository.findAll();
    }
}