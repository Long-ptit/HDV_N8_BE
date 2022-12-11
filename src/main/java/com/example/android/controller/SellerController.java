package com.example.android.controller;

import com.example.android.model.ResponseObject;
import com.example.android.model.Seller;
import com.example.android.model.User;
import com.example.android.repository.SellerRepository;
import com.example.android.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    SellerRepository sellerRepository;


    @PostMapping("/saveSeller")
    public ResponseObject getAllGood(@RequestBody Seller seller) {
       // System.out.println(seller.toString());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(null);
        responseObject.setMsg("Save user successfull");
        sellerRepository.save(seller);
        return responseObject;
    }


}
