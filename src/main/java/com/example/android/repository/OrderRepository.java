package com.example.android.repository;

import com.example.android.model.Cart;
import com.example.android.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

//    @Query(value="SELECT * FROM my_order WHERE id_user = ?1",nativeQuery = true)
//    Cart findCartOfUser(String idUser);

    @Query(value="SELECT * FROM my_order WHERE id_cart = ?1",nativeQuery = true)
    List<Order> findOrderByCart(int idCart);

    @Query(value="SELECT * FROM my_order WHERE id_seller = ?1",nativeQuery = true)
    List<Order> getOrderByIdSeller(String idSeller);

    @Query(value="SELECT * FROM my_order WHERE id_seller = ?1 and type_status = 3",nativeQuery = true)
    List<Order> getOrderSuccessByIdSeller(String idSeller);

    @Query(value="SELECT * FROM my_order WHERE id_seller = ?3 and type_status = 3 and create_at between ?1 and ?2",nativeQuery = true)
    List<Order> getDateBetween(long date1, long date2, String idSeller);

//    @Query(value="SELECT * FROM my_order WHERE create_at like %?1% and id_seller = ?2",nativeQuery = true)
//    List<Order> getDateLike(String key, String idSeller);

    @Query(value="SELECT * FROM my_order WHERE id_seller = ?3 and date_new between ?1 and ?2",nativeQuery = true)
    List<Order> getDateBetween1(Date date1, Date date2, String idSeller);

    @Query(value="SELECT * FROM my_order WHERE date_new like %?1% and id_seller = ?2",nativeQuery = true)
    List<Order> getDateLike(String key, String idSeller);

}
