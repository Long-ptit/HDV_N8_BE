package com.example.android.repository;

import com.example.android.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<ProductCategory, Integer> {
}
