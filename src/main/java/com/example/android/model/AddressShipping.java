package com.example.android.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class AddressShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String description;


}
