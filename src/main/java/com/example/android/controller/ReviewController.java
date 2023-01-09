package com.example.android.controller;


import com.example.android.model.*;
import com.example.android.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @PostMapping("/createReview")
    public ResponseObject createReview(@RequestBody Review review) {

        OrderItem orderItem = orderItemRepository.findById(review.getOrderItem().getId()).get();
        Product product = productRepository.findById(orderItem.getIdProduct()).get();
        product.setNumReview(product.getNumReview() + 1);
        productRepository.save(product);
        Review reviewSaved = reviewRepository.save(review);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(reviewSaved);
        responseObject.setMsg("Lưu dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getAll")
    public ResponseObject createReview() {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(reviewRepository.findAll());
        responseObject.setMsg("Lưu dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getRepresentReviewByProduct/{id}")
    public ResponseObject getRepresentReviewByProduct(@PathVariable("id") int id)
    {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(reviewRepository.getRepresentReviewByProduct(id));
        responseObject.setMsg("Lưu dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getAllReviewById/{id}")
    public ResponseObject getAllReviewById(@PathVariable("id") int id)
    {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(reviewRepository.getAllReviewByProduct(id));
        responseObject.setMsg("Lưu dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getStatisticReview/{id}")
    public ResponseObject getStatisticReview(@PathVariable("id") int id)
    {
        List<Review> listReview = reviewRepository.getAllReviewByProduct(id);
        List<StatisticReview> statisticReviews = new ArrayList<>();
        int sizeOfReview = listReview.size();
        int[] intArray = new int[5];
        int sumStar = 0;
        for(Review item: listReview) {
            intArray[item.getNumStars()-1]++;
            sumStar += item.getNumStars();
        }

        for(int i = 0; i < intArray.length; i++) {
            statisticReviews.add(new StatisticReview(i + 1, intArray[i], sumStar != 0 ? intArray[i]/(double) sumStar : 0));
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setTotalReview(sizeOfReview);
        reviewDTO.setAverageRate((double) sumStar/5*sizeOfReview);
        reviewDTO.setListStaReview(statisticReviews);

        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(reviewDTO);
        responseObject.setMsg("Lưu dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/testReview/{id}")
    public ResponseObject checkReview(@PathVariable("id") int id)
    {
        String result;
        String data;
        Review reviewOptional = reviewRepository.getReviewByOrderItem(id);
        if (reviewOptional != null) {
            result = "không thể đánh giá";
            data = "NO";
        } else {
            result = "có thể đánh giá";
            data = "YES";
        }

        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(data);
        responseObject.setMsg(result);
        return responseObject;
    }

}
