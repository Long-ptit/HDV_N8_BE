package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String deleteAtDate;
    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductCategory() {

    }
}
