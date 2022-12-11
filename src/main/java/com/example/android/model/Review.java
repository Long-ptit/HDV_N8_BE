package com.example.android.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int numStars;
    private String content;
    private long createAt;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;
}
