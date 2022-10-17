package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_seller")//name="tên cột khóa ngoại"
    Seller seller;

    @ManyToOne
    @JoinColumn(name = "id_category")//name="tên cột khóa ngoại"
    ProductCategory productCategory ;
}
