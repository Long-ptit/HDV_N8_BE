package com.example.android.controller;


import com.example.android.model.*;
import com.example.android.repository.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ShippingInformationRepository shippingInformationRepository;


    @PostMapping("/createOrder")
    public ResponseObject createOrder(@RequestBody Order order) throws ParseException {

        ShippingInformation shippingInformation =
                shippingInformationRepository.findById(order.getShippingInformation().getId()).get();
        order.setShippingInformation(shippingInformation);
        order.setCreateAt(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        Date date = format.parse(dateStr);
        order.setDateNew(date);


        Order orderSaved = orderRepository.save(order);
        List<CartItem> itemList = cartItemRepository.findAllCartItemByCartAndSeller(order.getCart().getId(), order.getSeller().getId());

        for (CartItem item : itemList) {
            OrderItem orderItem = new OrderItem();
            Product product = productRepository.findById(item.getProduct().getId()).get();
            orderItem.setName(item.getProduct().getName());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(item.getProduct().getPrice());
            orderItem.setIdProduct(item.getProduct().getId());
            orderItem.setProduct(product);
            orderItem.setOrder(orderSaved);
            orderItemRepository.save(orderItem);
            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }



        cartItemRepository.deleteCartItemByIdUserAndSeller(order.getCart().getId(), order.getSeller().getId());
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(orderSaved);
        responseObject.setMsg("Tạo đơn hàng thành công");
        return responseObject;
    }

    @GetMapping("/getOrderByUser/{id}")
    public ResponseObject getOrderByUser(@PathVariable("id") String id) {
        Cart cart = cartRepository.findCartOfUser(id);
        List<Order> listOrder = new ArrayList<>();
        if (orderRepository.findOrderByCart(cart.getId()) != null) {
            listOrder = orderRepository.findOrderByCart(cart.getId());
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(listOrder);
        responseObject.setMsg("Lấy dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseObject getOrderById(@PathVariable("id") int id) {
        Order order = orderRepository.findById(id).get();
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(order);
        responseObject.setMsg("Lấy dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getOrderItemByOrder/{id}")
    public ResponseObject getOrderItemByOrder(@PathVariable("id") int id) {
        List<OrderItem> listOrderItem = orderItemRepository.findOrderItemByOrder(id);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(listOrderItem);
        responseObject.setMsg("Lấy dữ liệu thành công");
        return responseObject;
    }

    @GetMapping("/getOrderByIdSeller/{id}")
    public ResponseObject getOrderByIdSeller(@PathVariable("id") String idSeller) {
        List<Order> order = orderRepository.getOrderByIdSeller(idSeller);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(order);
        responseObject.setMsg("Lấy dữ liệu thành công");
        return responseObject;
    }

    @PostMapping("/changeStatus")
    public ResponseObject changeStatusOrder(
            @RequestParam("id_order") int idOrder,
            @RequestParam("type_status") int type_status
    ) {
        Order order = orderRepository.findById(idOrder).get();
        order.setTypeStatus(type_status);

        if (type_status == 3) {
            List<OrderItem> listOrderItem = orderItemRepository.findOrderItemByOrder(idOrder);
            for(OrderItem item: listOrderItem) {
                Product product = productRepository.findById(item.getIdProduct()).get();
                System.out.println();
               // product.setQuantity(product.getQuantity() - item.getQuantity());
                product.setSoldNumber(product.getSoldNumber() + item.getQuantity());
                productRepository.save(product);
            }
        }

        if (type_status == 4) {
            List<OrderItem> listOrderItem = orderItemRepository.findOrderItemByOrder(idOrder);
            for(OrderItem item: listOrderItem) {
                Product product = productRepository.findById(item.getIdProduct()).get();
                System.out.println();
                 product.setQuantity(product.getQuantity() + item.getQuantity());
                //product.setSoldNumber(product.getSoldNumber() + item.getQuantity());
                productRepository.save(product);
            }
        }

        orderRepository.save(order);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(order);
        responseObject.setMsg("Thay doi trang thai don hang thanh cong");
        return responseObject;
    }
}
