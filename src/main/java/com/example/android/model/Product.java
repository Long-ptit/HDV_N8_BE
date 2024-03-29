package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private int discountPoint;
    private boolean discount = false;
    private String msgDiscount;
    private String specification;
    private String deleteAt;
    private int soldNumber;
    private int numReview;
    private String yearPublish;

    @ManyToOne
    @JoinColumn(name = "id_seller")//name="tên cột khóa ngoại"
    Seller seller;

    @ManyToOne
    @JoinColumn(name = "id_category")//name="tên cột khóa ngoại"
    ProductCategory productCategory ;

    @ManyToOne
    @JoinColumn(name = "id_manufacturer")//name="tên cột khóa ngoại"
    Manufacturer manufacturer ;
}
