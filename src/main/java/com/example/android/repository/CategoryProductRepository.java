package com.example.android.repository;

import com.example.android.model.ProductCategory;
import com.example.android.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryProductRepository extends JpaRepository<ProductCategory, Integer> {

    @Query(value = "SELECT * FROM product_category WHERE delete_at_date is null", nativeQuery = true)
    List<ProductCategory> getAllValidProductCategory();

}
