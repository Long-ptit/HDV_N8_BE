package com.example.android.repository;

import com.example.android.model.CartItem;
import com.example.android.model.Review;
import com.example.android.model.ShippingInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingInformationRepository extends JpaRepository<ShippingInformation, Integer> {

    @Query(value="SELECT * FROM shipping_information WHERE is_default = 1 and id_user = ?1",nativeQuery = true)
    ShippingInformation getDefault(String id);

    @Query(value="SELECT * FROM shipping_information WHERE id_user = ?1 and delete_at is null",nativeQuery = true)
    List<ShippingInformation> getAll(String id);



}
