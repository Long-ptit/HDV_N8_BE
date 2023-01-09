package com.example.android.controller;

import com.example.android.model.Manufacturer;
import com.example.android.model.Product;
import com.example.android.model.ProductCategory;
import com.example.android.repository.CategoryProductRepository;
import com.example.android.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;



    @GetMapping("getAllCategory")
    public List<ProductCategory> getAllCategory() {
        return categoryProductRepository.getAllValidProductCategory();
    }

    @GetMapping("getAllManu")
    public List<Manufacturer> getAllManu() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("getCategory/{id}")
    public ProductCategory getCategory(@PathVariable("id") int id ) {
        return categoryProductRepository.findById(id).get();
    }

    @PostMapping("saveCategory")
    public ProductCategory saveCategory(
            @RequestParam("category_img") MultipartFile file,
            @RequestParam("category_name") String category_name,
            @RequestParam("category_description") String category_description
    ) throws IOException {

        ProductCategory productCategory = categoryProductRepository.save(new ProductCategory(category_name, category_description));

        String folder = "photos_product/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(productCategory.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return productCategory;
    }

    @PostMapping("editCategory")
    public ProductCategory editCategory(
            @RequestParam("category_img") MultipartFile file,
            @RequestParam("category_name") String category_name,
            @RequestParam("category_description") String category_description,
            @RequestParam("category_id") String category_id
    ) throws IOException {

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(category_name);
        productCategory.setDescription(category_description);
        productCategory.setId(Integer.parseInt(category_id));
        categoryProductRepository.save(productCategory);

        String folder = "photos_product/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(category_id + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return new ProductCategory();
    }

    @GetMapping("deleteCategory/{id}")
    public ProductCategory deleteCategory(@PathVariable("id") int id ) {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        ProductCategory productCategory = categoryProductRepository.findById(id).get();
        productCategory.setDeleteAtDate(date);
        categoryProductRepository.save(productCategory);

        return productCategory;
    }





}
