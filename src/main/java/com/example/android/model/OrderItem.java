package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private long price;
    private int quantity;
    private int idProduct;

    @ManyToOne
    @JoinColumn(name = "id_order")//name="tên cột khóa ngoại"
    Order order;

    @ManyToOne
    @JoinColumn(name = "id_product_data")//name="tên cột khóa ngoại"
    Product product;








}
