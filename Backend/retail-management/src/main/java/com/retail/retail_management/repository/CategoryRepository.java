package com.retail.retail_management.repository;

import com.retail.retail_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}