package com.example.android.controller;

import com.example.android.model.ProductCategory;
import com.example.android.repository.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @GetMapping("getAllCategory")
    public List<ProductCategory> getAllCategory() {
        return categoryProductRepository.findAll();
    }

}
