package com.example.android.controller;

import com.example.android.model.*;
import com.example.android.repository.CartRepository;
import com.example.android.repository.SellerRepository;
import com.example.android.repository.ShippingInformationRepository;
import com.example.android.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Autowired
    ShippingInformationRepository shippingInformationRepository;


    @PostMapping("/saveUser")
    public ResponseObject saveUser(@RequestBody User user) {
        System.out.println(user.toString());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(null);
        user.setUserType("USER");
        responseObject.setMsg("Save user successfull");
        User userSaved = userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(userSaved);
        cart.setCreateAt(System.currentTimeMillis());
        cart.setTotalPrice(0);
        cartRepository.save(cart);

        ShippingInformation shippingInformation = new ShippingInformation();
        shippingInformation.setUser(userSaved);
        shippingInformation.setIsDefault(true);
        shippingInformation.setName(userSaved.getName());
        shippingInformation.setPhone(userSaved.getPhone());
        shippingInformation.setAddress(userSaved.getAddress());
        shippingInformationRepository.save(shippingInformation);

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
    @RequestMapping("/getInforUser/{id}")
    public User getInfor(@PathVariable("id") String id) {
        return userRepository.findById(id).get();
    }

    @RequestMapping("/getInforSeller/{id}")
    public ResponseObject getInforSeller(@PathVariable("id") String id) {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMsg("Lay thanh cong");
        responseObject.setData(sellerRepository.findById(id).get());

        return responseObject;
    }

    @PostMapping("saveImage")
    public ResponseObject saveImage(
            @RequestParam("user_img") MultipartFile file,
            @RequestParam("user_id") String user_id

    ) throws IOException {
        String folder = "photos_product/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(user_id + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return new ResponseObject();
    }


}
