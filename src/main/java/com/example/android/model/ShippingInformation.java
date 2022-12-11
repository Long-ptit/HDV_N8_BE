package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class ShippingInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String phone;
    private String address;
    private Boolean isDefault = false;

    @ManyToOne
    @JoinColumn(name = "id_user")//name="tên cột khóa ngoại"
    User user;




}
