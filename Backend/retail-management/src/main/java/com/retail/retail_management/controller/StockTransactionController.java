package com.retail.retail_management.controller;

import com.retail.retail_management.entity.StockTransaction;
import com.retail.retail_management.entity.TransactionType;
import com.retail.retail_management.service.StockTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "*")
public class StockTransactionController {

    private final StockTransactionService stockTransactionService;

    public StockTransactionController(
            StockTransactionService stockTransactionService) {
        this.stockTransactionService = stockTransactionService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<StockTransaction> addStockTransaction(
            @PathVariable Long productId,
            @RequestParam TransactionType type,
            @RequestParam Integer quantity,
            @RequestParam(required = false) String description) {

        return ResponseEntity.ok(
                stockTransactionService.addStockTransaction(
                        productId,
                        type,
                        quantity,
                        description
                )
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<StockTransaction>>
    getProductTransactions(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                stockTransactionService
                        .getTransactionsByProduct(productId)
        );
    }

    @GetMapping
    public ResponseEntity<List<StockTransaction>>
    getAllTransactions() {

        return ResponseEntity.ok(
                stockTransactionService.getAllTransactions()
        );
    }
}