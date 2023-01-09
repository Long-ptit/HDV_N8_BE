package com.example.android.controller;


import com.example.android.model.*;
import com.example.android.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @PostMapping("/saveAddress")
    public ShippingInformation saveAddress(@RequestBody ShippingInformation data) {
        if (data.getIsDefault()) {
            ShippingInformation defaultData = shippingInformationRepository.getDefault(data.getUser().getId());
            defaultData.setIsDefault(false);
            shippingInformationRepository.save(defaultData);
        }
        ShippingInformation informationSaved = shippingInformationRepository.save(data);

        return informationSaved;
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

    @GetMapping("/getById/{id}")
    public ShippingInformation getById(@PathVariable("id") int id) {
        ShippingInformation informationSaved = shippingInformationRepository.findById(id).get();
        return informationSaved;
    }

    @GetMapping("/deleteAddress/{id}")
    public ShippingInformation deleteAddress(@PathVariable("id") int id) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        ShippingInformation information = shippingInformationRepository.findById(id).get();
        if (information.getIsDefault()) return null;
        information.setDeleteAt(date);
        ShippingInformation shippingInformationSaved =  shippingInformationRepository.save(information);
        return shippingInformationSaved;
    }


}
