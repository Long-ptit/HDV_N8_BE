package com.example.android.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table
public class Seller extends User implements Serializable {

    private String shopName;

    @Override public String toString() {
        return shopName + super.toString();
    }


}
