package com.example.android.repository;

import com.example.android.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value="SELECT * FROM cart WHERE id_user = ?1",nativeQuery = true)
    Cart findCartOfUser(String idUser);



}
