package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private long createAt;
    private long totalPrice;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_user")//name="tên cột khóa ngoại"
    User user;

}
