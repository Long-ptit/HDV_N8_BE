package com.example.android.model;

import lombok.Data;

@Data
public class StatisticReview {
    private int typeStar;
    private int quantity;
    private double percent;

    public StatisticReview(int typeStar, int quantity, double percent) {
        this.typeStar = typeStar;
        this.quantity = quantity;
        this.percent = percent;
    }
}
