package com.example.android.controller;


import com.example.android.model.Cart;
import com.example.android.model.CartItem;
import com.example.android.model.Product;
import com.example.android.model.ResponseObject;
import com.example.android.repository.CartItemRepository;
import com.example.android.repository.CartRepository;
import com.example.android.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getCartById/{id}")
    public ResponseObject getCartByIdUser(@PathVariable("id") String id) {
        Cart cart = cartRepository.findCartOfUser(id);
        List<CartItem> listCartItem = cartItemRepository.findAllCartItemByCart(cart.getId());
        long sumPrice =  0;
        int sumQuantity = 0;

        for (CartItem item: listCartItem) {
            if (item.getProduct().isDiscount()) {
                sumPrice += (long) item.getProduct().getPrice() * item.getQuantity() * (100- item.getProduct().getDiscountPoint())/100
                ;
            } else {
                sumPrice += (long) item.getProduct().getPrice() * item.getQuantity();
            }
            sumQuantity += item.getQuantity();
        }
        cart.setTotalPrice(sumPrice);
        cart.setTotalQuantity(sumQuantity);
        Cart cartSaved = cartRepository.save(cart);

        ResponseObject responseObject = new ResponseObject();
        responseObject.setMsg("success");
        responseObject.setData(cartSaved);
        return  responseObject;
    }

    @GetMapping("/addItemToCart")
    public ResponseObject addItemToCart(
            @RequestParam("product_id") int product_id,
            @RequestParam("user_id") String user_id,
            @RequestParam("quantity") int quantity
    ) {
        Product product = productRepository.findById(product_id).get();

        Cart cart = cartRepository.findCartOfUser(user_id);
        CartItem cartItem = cartItemRepository.findCartItemByIdCartAndProduct(cart.getId(), product_id);
        System.out.println("Quantity" + quantity);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);

            int finalQuantity = quantity + cartItem.getQuantity();
            if (product.getQuantity() < finalQuantity) {
                ResponseObject responseObject = new ResponseObject();
                responseObject.setData(null);
                responseObject.setMsg("Bạn cần nhập số lượng nhỏ hơn");

                return responseObject;
            }
        } else {

            if (product.getQuantity() < quantity) {
                ResponseObject responseObject = new ResponseObject();
                responseObject.setData(null);
                responseObject.setMsg("Bạn cần nhập số lượng nhỏ hơn");

                return responseObject;
            }

            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setCreateAt(0);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(productRepository.findById(product_id).get());

        }
        cartItemRepository.save(cartItem);



        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(cartItem);
        responseObject.setMsg("success");
        return responseObject;
    }

    @GetMapping("/getListCartItemByIdUser/{id}")
    public List<CartItem> getListCartItemByIdUser(@PathVariable("id") String id) {
        Cart cart = cartRepository.findCartOfUser(id);
        if (cart == null) return new ArrayList<>();

        List<CartItem> cartItemList = cartItemRepository.findAllCartItemByCart(cart.getId());

        return cartItemList;
    }

    /**
     * type
     * 0 -> tang
     * 1 -> giam
     */
    @GetMapping("/changeQuantity")
    public ResponseObject changeQuantity(@RequestParam("id") int id, @RequestParam("type") int type) {
        CartItem cartItem = cartItemRepository.findById(id).get();
        if (type == 0) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
        CartItem itemSaved = cartItemRepository.save(cartItem);

        ResponseObject responseObject = new ResponseObject();
        responseObject.setMsg("success");
        responseObject.setData(itemSaved);

        return responseObject;
    }

    @GetMapping("/deleteCartItem")
    public ResponseObject deleteCartItem(@RequestParam("id") int id) {
       cartItemRepository.deleteById(id);

        ResponseObject responseObject = new ResponseObject();
        responseObject.setMsg("success");
        responseObject.setData(null);

        return responseObject;
    }


    @GetMapping("/test")
    public String test1() {
        CartItem cartItem = cartItemRepository.findCartItemByIdCartAndProduct(5,5);
        if (cartItem == null) {
            System.out.println("null");
        } else {
            System.out.println("khong null");
        }
        return "kaka";
    }


}
