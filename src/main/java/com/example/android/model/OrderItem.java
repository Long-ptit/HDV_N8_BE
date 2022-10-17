package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "id_order")//name="tên cột khóa ngoại"
    Order order;





}
