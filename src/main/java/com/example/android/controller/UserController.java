package com.example.android.controller;

import com.example.android.model.Cart;
import com.example.android.model.ResponseObject;
import com.example.android.model.Seller;
import com.example.android.model.User;
import com.example.android.repository.CartRepository;
import com.example.android.repository.SellerRepository;
import com.example.android.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CartRepository cartRepository;




    @PostMapping("/saveUser")
    public ResponseObject saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(null);
        responseObject.setMsg("Save user successfull");
        User userSaved = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(userSaved);
        cart.setCreateAt(System.currentTimeMillis());
        cart.setTotalPrice(0);
        cartRepository.save(cart);

        return responseObject;
    }

    @RequestMapping("/allUser")
    public ResponseObject getAllUser() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(userRepository.findAll());
        responseObject.setMsg("Save user successfull");


        return responseObject;
    }
//
//    @RequestMapping("/getInfor/{id}")
//    public ResponseObject getInfor(@PathVariable("id") String id) {
//        ResponseObject responseObject = new ResponseObject();
//
//        Optional<Seller> sellerOptional = sellerRepository.findById(id);
//        if (sellerOptional)
//
//        return responseObject;
//    }


}
