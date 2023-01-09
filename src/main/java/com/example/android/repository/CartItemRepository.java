package com.example.android.repository;

import com.example.android.model.Cart;
import com.example.android.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query(value="SELECT * FROM cart_item WHERE id_cart = ?1",nativeQuery = true)
    List<CartItem> findAllCartItemByCart(int idCart);

    @Query(value="SELECT * FROM cart_item join product on product.id = id_product where id_cart = ?1 and product.id_seller = ?2",nativeQuery = true)
    List<CartItem> findAllCartItemByCartAndSeller(int idCart, String idSeller);

    @Query(value="SELECT * FROM cart_item WHERE id_cart = ?1 and id_product = ?2",nativeQuery = true)
    CartItem findCartItemByIdCartAndProduct(int idCart, int idProduct);


    @Transactional
    @Modifying
    @Query(value="DELETE FROM cart_item WHERE id_cart = ?1",nativeQuery = true)
    void deleteCartItemByIdUser(int idCart);

    @Transactional
    @Modifying
    @Query(value="DELETE cart_item FROM cart_item join product on product.id = id_product where id_cart = ?1 and product.id_seller = ?2",nativeQuery = true)
    void deleteCartItemByIdUserAndSeller(int idCart, String sellerId);


}
