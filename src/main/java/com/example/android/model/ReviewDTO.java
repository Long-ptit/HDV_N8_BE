package com.example.android.model;


import lombok.Data;

import java.util.List;

@Data
public class ReviewDTO {
    private List<StatisticReview> listStaReview;
    private double averageRate;
    private int totalReview;
}
