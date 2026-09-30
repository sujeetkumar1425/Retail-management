package com.retail.retail_management.dto;

public class DashboardSummary {

    private long totalProducts;
    private long totalCategories;
    private long totalStock;
    private long lowStockProducts;

    public DashboardSummary() {
    }

    public DashboardSummary(long totalProducts,
                            long totalCategories,
                            long totalStock,
                            long lowStockProducts) {
        this.totalProducts = totalProducts;
        this.totalCategories = totalCategories;
        this.totalStock = totalStock;
        this.lowStockProducts = lowStockProducts;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public long getTotalCategories() {
        return totalCategories;
    }

    public void setTotalCategories(long totalCategories) {
        this.totalCategories = totalCategories;
    }

    public long getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(long totalStock) {
        this.totalStock = totalStock;
    }

    public long getLowStockProducts() {
        return lowStockProducts;
    }

    public void setLowStockProducts(long lowStockProducts) {
        this.lowStockProducts = lowStockProducts;
    }
}