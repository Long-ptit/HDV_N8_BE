package com.example.android.controller;


import com.example.android.model.*;
import com.example.android.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ship")
public class ShippingInformationController {

    @Autowired
    ShippingInformationRepository shippingInformationRepository;


    @PostMapping("/createOrder")
    public ResponseObject createOrder(@RequestBody ShippingInformation data) {
        ShippingInformation informationSaved = shippingInformationRepository.save(data);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(informationSaved);
        responseObject.setMsg("Tạo địa chỉ thành công");
        return responseObject;
    }

    @GetMapping("/getDefault/{id}")
    public ShippingInformation getDefault(@PathVariable("id") String id) {
        ShippingInformation informationSaved = shippingInformationRepository.getDefault(id);

        return informationSaved;
    }

    @GetMapping("/getAllShip/{id}")
    public List<ShippingInformation> getAllShip(@PathVariable("id") String id) {
        List<ShippingInformation> informationSaved = shippingInformationRepository.getAll(id);
        return informationSaved;
    }



}
