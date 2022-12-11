package com.example.android.repository;

import com.example.android.model.Cart;
import com.example.android.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query(value="SELECT * FROM order_item WHERE id_order = ?1",nativeQuery = true)
    List<OrderItem> findOrderItemByOrder(int idOrder);

    @Query(value="SELECT * FROM order_item WHERE id_seller = ?1",nativeQuery = true)
    List<OrderItem> findOrderItemBySeller(String idOrder);



}
