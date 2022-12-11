package com.example.android.repository;

import com.example.android.model.OrderItem;
import com.example.android.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    @Query(value="SELECT * FROM order_item WHERE id_order = ?1",nativeQuery = true)
//    List<OrderItem> findOrderItemByOrder(int idOrder);
//
    @Query(value="SELECT * FROM review WHERE id_product = ?1 LIMIT 2",nativeQuery = true)
    List<Review> getRepresentReviewByProduct(int idProduct);

    @Query(value="SELECT * FROM review WHERE id_product = ?1",nativeQuery = true)
    List<Review> getAllReviewByProduct(int idProduct);



}
