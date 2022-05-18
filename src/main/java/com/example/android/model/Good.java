package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
