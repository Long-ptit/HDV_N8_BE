package com.example.android.controller;

import com.example.android.model.Good;
import com.example.android.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food")
public class TestController {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/all")
    public List<Good> getAllGood() {
        return goodRepository.findAll();
    }



}
