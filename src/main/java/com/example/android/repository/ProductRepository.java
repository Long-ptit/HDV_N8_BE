package com.example.android.repository;

import com.example.android.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value="SELECT * FROM product WHERE id_seller=?1",nativeQuery = true)
    List<Product> getProductBySeller(String idSeller);

}
