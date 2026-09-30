package com.retail.retail_management.service;

import com.retail.retail_management.entity.Product;
import com.retail.retail_management.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
//    public List<Product> getLowStockProducts() {
//        return productRepository.findByQuantityLessThanEqual(
//                10
//        );
//    }

    public Product updateProduct(Long id, Product updatedProduct) {

        return productRepository.findById(id)
                .map(product -> {

                    product.setName(updatedProduct.getName());
                    product.setSku(updatedProduct.getSku());
                    product.setPrice(updatedProduct.getPrice());
                    product.setQuantity(updatedProduct.getQuantity());
                    product.setReorderLevel(updatedProduct.getReorderLevel());
                    product.setCategory(updatedProduct.getCategory());

                    return productRepository.save(product);
                })
                .orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}