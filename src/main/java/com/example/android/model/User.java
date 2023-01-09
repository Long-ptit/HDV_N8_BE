package com.example.android.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private long createAt;
    private String phone;
    private String userType;


}
