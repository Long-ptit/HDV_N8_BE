package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String phone;
    private String yearPublish;
    private String email;
    private String taxNumber;
}
