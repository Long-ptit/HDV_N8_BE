package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private long createAt;
    private String phone;
    private int role;
    private String uid;


}
