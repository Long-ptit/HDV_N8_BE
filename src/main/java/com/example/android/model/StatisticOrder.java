package com.example.android.model;

import lombok.Data;

@Data
public class StatisticOrder extends Order{
    private long statisticAmount;
    private int statisticQuantity;
}
