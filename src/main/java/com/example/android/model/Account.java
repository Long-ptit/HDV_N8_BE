package com.example.android.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class Account implements Serializable {
    private String email;
    private int role;

    @ManyToOne
    @JoinColumn(name = "id_people")//name="tên cột khóa ngoại"
    private User user;
}
