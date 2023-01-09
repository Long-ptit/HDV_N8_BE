package com.example.android.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "my_order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
    private long createAt;
    private long totalPrice;
    private int totalQuantity;
    private String description;
    private Boolean isSuccess;
    private int typeStatus;
    private String paymentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    Date dateNew;

    @ManyToOne
    @JoinColumn(name = "id_cart")//name="tên cột khóa ngoại"
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_shipping")//name="tên cột khóa ngoại"
    ShippingInformation ShippingInformation;

    @ManyToOne
    @JoinColumn(name = "id_seller")//name="tên cột khóa ngoại"
    Seller seller;


}
