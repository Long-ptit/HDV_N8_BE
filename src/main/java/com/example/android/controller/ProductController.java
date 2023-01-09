package com.example.android.controller;

import com.example.android.model.*;
import com.example.android.repository.CategoryProductRepository;
import com.example.android.repository.ManufacturerRepository;
import com.example.android.repository.ProductRepository;
import com.example.android.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    //from youtube with lo` ve'
    @PostMapping("saveProduct")
    public Product saveFood(
            @RequestParam("product_img") MultipartFile file,
            @RequestParam("product_id") String product_id
    ) throws IOException {


        String folder = "photos_product/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(product_id + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return new Product();
    }

    @PostMapping("saveProductWithoutImage")
    public Product saveProduct(
           @RequestBody Product product
    ) {
        Seller seller = sellerRepository.findById(product.getSeller().getId()).get();
        ProductCategory productCategory = categoryProductRepository.findById(product.getProductCategory().getId()).get();
        Manufacturer manufacturer = manufacturerRepository.findById(product.getManufacturer().getId()).get();
        boolean isDiscount = product.isDiscount();
        if (isDiscount) {
            System.out.println("kaka");
        }
        product.setProductCategory(productCategory);
        product.setSeller(seller);
        product.setManufacturer(manufacturer);
        product.setDiscount(isDiscount);

        Product productSave = productRepository.save(product);
        return productSave;
    }
    

    //from youtube with love
    @ResponseBody
    @GetMapping("getImage/{photo}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo) throws IOException {
        Path fileName = Paths.get("photos_product", photo);
        byte[] buffer = Files.readAllBytes(fileName);
        ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
        return ResponseEntity.ok()
                .contentLength(buffer.length)
                .contentType(MediaType.parseMediaType("image/png"))
                .body(byteArrayResource);
    }

    @GetMapping(value = "getAllProduct")
    public List<Product> getAllProduct() {
        List<Product> listProduct = productRepository.findAll();
//        ResponseObject responseObject = new ResponseObject();
//        responseObject.setData(listProduct);
//        responseObject.setMsg("Send data success");

        return listProduct;
    }

    @GetMapping(value = "getProductBySeller/{id}")
    public List<Product> getProductByID(@PathVariable String id) {
        List<Product> listProduct = productRepository.getProductBySeller(id.trim());
//        ResponseObject responseObject = new ResponseObject();
//        responseObject.setData(listProduct);
//        responseObject.setMsg("Send data success");

        return listProduct;
    }

    @PostMapping(value = "searchProduct")
    public List<Product> searchProduct(@RequestParam("keyword") String key) {
        List<Product> listProduct = productRepository.searchProductByName(key);
//        ResponseObject responseObject = new ResponseObject();
//        responseObject.setData(listProduct);
//        responseObject.setMsg("Send data success");

        return listProduct;
    }



    @GetMapping(value = "getTest")
    public ResponseObject getObject() throws UnknownHostException {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMsg("Success");
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        System.out.println(InetAddress.getLocalHost().getHostName());
        int port = serverProperties.getPort();
        responseObject.setData(host + "port: " + port);
        return responseObject;
    }


    @GetMapping(value = "getProductById/{id}")
    public Product getProductById(@PathVariable int id) {
        return productRepository.findById(id).get();
    }

    @PostMapping("saveEditProduct")
    public Product saveEditFood(
            @RequestParam("product_img") MultipartFile file,
            @RequestParam("seller_id") String seller_id,
            @RequestParam("product_id") String product_id,
            @RequestParam("product_name") String product_name,
            @RequestParam("product_description") String product_description,
            @RequestParam("product_quantity") int product_quantity,
            @RequestParam("product_price") int product_price
    ) throws IOException {
        System.out.println(product_name);
        Product product = new Product();
        product.setName(product_name);
        product.setDescription(product_description);
        product.setQuantity(product_quantity);
        product.setPrice(product_price);
        System.out.println(seller_id);
        product.setSeller(sellerRepository.findById(seller_id.trim()).get());

        Product productSave = productRepository.save(product);

        String folder = "photos_product/";
        Path path = Paths.get(folder);
        InputStream inputStream = file.getInputStream();
        Files.copy(inputStream, path.resolve(productSave.getId() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(file.getOriginalFilename());

        return productSave;
    }

}
