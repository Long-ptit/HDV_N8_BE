package com.example.android.repository;

import com.example.android.model.Cart;
import com.example.android.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value="SELECT * FROM cart_item WHERE id_cart = ?1",nativeQuery = true)
    List<CartItem> findAllCartItemByCart(int idCart);

    @Query(value="SELECT * FROM cart_item WHERE id_cart = ?1 and id_product = ?2",nativeQuery = true)
    CartItem findCartItemByIdCartAndProduct(int idCart, int idProduct);


}