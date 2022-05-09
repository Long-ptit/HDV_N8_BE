//package com.example.android.controller;
//
//import com.example.android.model.Food;
//import com.example.android.repository.FoodRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/food")
//public class FoodController {
//
//    @Autowired
//    FoodRepository foodRepository;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @RequestMapping("/all")
//    public List<Food> getAllFood() {
//        return foodRepository.findAll();
//    }
//
//    @RequestMapping("/sendEmail")
//    public String sendEmal() {
//        Thread sendMailThread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                SimpleMailMessage message = new SimpleMailMessage();
//                message.setFrom("noreply@petshop.com");
//                message.setTo("trinhxuanlong0612@gmail.com");
//                message.setSubject("Thông báo: Đơn hàng đã được tạo");
//                message.setText("haha");
//                javaMailSender.send(message);
//                System.out.println("Đã send email");
//            }
//        });
//        sendMailThread.start();
//
//        return "Test";
//    }
//
//
//}
