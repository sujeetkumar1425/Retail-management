package com.retail.retail_management.service;

import com.retail.retail_management.dto.DashboardSummary;
import com.retail.retail_management.repository.CategoryRepository;
import com.retail.retail_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DashboardService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public DashboardSummary getSummary() {

        long totalProducts = productRepository.count();

        long totalCategories = categoryRepository.count();

        long totalStock = productRepository.findAll()
                .stream()
                .mapToLong(product -> product.getQuantity())
                .sum();

        long lowStockProducts =
                productRepository.findLowStockProducts().size();

        return new DashboardSummary(
                totalProducts,
                totalCategories,
                totalStock,
                lowStockProducts
        );
    }
}