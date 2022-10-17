package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long createAt;
    private long totalPrice;
    private int totalQuantity;

    @OneToOne
    @JoinColumn(name = "id_user")//name="tên cột khóa ngoại"
    private User user;


}
