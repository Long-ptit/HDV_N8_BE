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

    /**
     * @param idProduct
     * @return
     * @Transactional
     * @Modifying
     * @Query(value="DELETE cart_item FROM cart_item join product on product.id = id_product where id_cart = ?1 and id_seller = ?2",nativeQuery = true)
     * void deleteCartItemByIdUserAndSeller(int idCart, String sellerId);
     */

    @Query(value = "SELECT * FROM review join order_item on order_item.id = id_order_item and order_item.id_product_data  = ?1 LIMIT 2", nativeQuery = true)
    List<Review> getRepresentReviewByProduct(int idProduct);

    @Query(value = "SELECT * FROM review join order_item on order_item.id = id_order_item and order_item.id_product_data = ?1", nativeQuery = true)
    List<Review> getAllReviewByProduct(int idProduct);


    @Query(value = "SELECT * FROM review WHERE id_order_item = ?1", nativeQuery = true)
    Review getReviewByOrderItem(int idOrderItem);


}
