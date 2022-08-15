package com.example.demo.controller;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.entity.Product;
import com.example.demo.services.CustomerServices;
import com.example.demo.services.ProductServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductServices productServices;
    private final CustomerServices customerServices;
    @Autowired
    public ProductController(ProductServices productServices, CustomerServices customerServices) {
        this.productServices = productServices;
        this.customerServices = customerServices;
    }
/*    @GetMapping("/")
    @JsonView(View.View2.class)
    public  List<Product> productList(@RequestParam(required = false) Long productId, @RequestParam(required = false) Boolean like, @RequestBody(required = false) CommentDTO commentDTO,@RequestParam(required = false) Long deleteComment,Principal principal){
        return productServices.listProduct(productId,like,commentDTO,deleteComment,principal.getName());

    }*/
    @PostMapping()
    public void addProductComment(@RequestParam(required = true) Long productId,  @RequestBody(required = true) CommentDTO commentDTO,Principal principal){
        productServices.addProductComment(productId,commentDTO,principal.getName());
    }
    @PutMapping()
    public void addProductLike(@RequestParam(required = true) Long productId, @RequestParam(required = true)Boolean like ,Principal principal){
        productServices.addProductLike(productId,like,principal.getName());
    }
    @DeleteMapping()
    public void deleteProductComment(@RequestParam(required = true) Long productId,@RequestParam(required = true) Long deleteComment  ,Principal principal){
        productServices.deleteProductComment(productId,deleteComment,principal.getName());
    }


    @GetMapping(path="/{id}/product-comments")
    public List<Product> showProductComments(@PathVariable Long id) {

        return customerServices.showProductComment(id);
    }
    @JsonView(View.View2.class)
    @GetMapping
    public List <Product> filterProduct(@RequestParam(required = false)String categoury,
                                        @RequestParam(required = false)String color,
                                        @RequestParam(required = false)List<String> size,
                                        @RequestParam(required = false)String type,
                                        @RequestParam(required = false)String brand,
                                        @RequestParam (required = false)Integer s,
                                        @RequestParam(required = false)Integer e
                                        ){
    return productServices.filter(categoury,color,size,type,brand,s,e);
    }

    @GetMapping("/numberOfLike")
    public Integer numberOfLikesOnProduct(@RequestParam(required = true)Long productId){
        return productServices.numberOfLikesOnProduct(productId);
    }
}

