package com.example.android.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table
public class Admin extends User implements Serializable {

    private String description;


}
