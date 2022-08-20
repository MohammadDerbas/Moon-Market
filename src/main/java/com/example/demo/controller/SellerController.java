package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.SellerServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("seller")
public class SellerController {
    private final SellerServices sellerServices;
    private final UserRepo userRepo;

    @Autowired

    public SellerController(SellerServices sellerServices, UserRepo userRepo) {

        this.sellerServices = sellerServices;
        this.userRepo = userRepo;
    }

    @GetMapping("/{id}/info")
    @JsonView(View.View1.class)
    public Optional<Seller> infoSeller(@PathVariable("id") Long id) {

        return sellerServices.getSeller(id);
    }
    @DeleteMapping("/{id}/info")
    public void deleteCommentFromSellerComments(@PathVariable("id") Long id,@RequestParam(required = true)Long deleteComment,Principal principal){
        sellerServices.deleteCommentFromSellerComments(id,deleteComment,principal.getName());
    }

    @PostMapping("/{id}/info")
    public void addCommentToSeller(@PathVariable("id") Long id,@RequestBody(required = true)CommentDTO commentDTO,Principal principal){
        sellerServices.addCommentToSeller(id,commentDTO,principal.getName());
    }
    @PutMapping("/{id}/info")
    public void addFollow(@PathVariable("id") Long id,@RequestParam(required = true) Boolean follow,Principal principal){
        sellerServices.addFollow(id,follow,principal.getName());
    }

    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")

    @PutMapping("/{id}/update")
    public void updateSellerInfo(@PathVariable Long id,
                                 @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName,
                                 @RequestParam(required = false) String email,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) String address,
                                 @RequestParam(required = false) String phone,
                                 @RequestParam(required = false) String postalCode) {
        sellerServices.updateSellerInfo(id, firstName, lastName, email, password, address, phone, postalCode);
    }
    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")
    @PostMapping(value = "{id}/product/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewProduct(@PathVariable Long id,   @RequestParam(name = "description") String description,
                              @RequestParam(name="quantity") Integer quantity,
                              @RequestParam(name="price") Double price,
                              @RequestParam(name="type") String type,
                              @RequestParam(name="brand") String brand,
                              @RequestParam(name="category") String category,
                              @RequestParam(name="sizes[]") List<String> sizes,
                              @RequestParam(name="color[][color]")List<String> color,
                              @RequestParam(name="color[][images][]")List<MultipartFile> images

                              ) throws IOException {
        sellerServices.addNewProduct(id,description,quantity,price,type,brand,category,sizes,color,images);
    }

    @GetMapping("{id}/product")
    @JsonView(View.View2.class)
    public List<Product> showProduct(@PathVariable Long id) {
        return sellerServices.showProduct(id);
    }
    @PostMapping("{id}/product")
    public void addProductComment1(@PathVariable Long id, @RequestParam (required = true)Long productId,@RequestBody(required = true) CommentDTO commentDTO,Principal principal){
        sellerServices.addComment1(id,productId,commentDTO,principal.getName());
    }
    @DeleteMapping ("{id}/product")
    public void deleteProductComment1(@PathVariable Long id, @RequestParam (required = true)Long productId,@RequestParam(required = true)Long deleteComment,Principal principal){
        sellerServices.deleteComment1(id,productId,deleteComment,principal.getName());
    }
    @PutMapping("{id}/product")
    public void addProductLike1(@PathVariable Long id, @RequestParam (required = true)Long productId,@RequestParam(required = true) Boolean like,Principal principal){
        sellerServices.addLike1(id,productId,like,principal.getName());
    }


    @GetMapping("{id}/product/{id2}")
    @JsonView(View.View2.class)


    public Product InfoSellerProductWithId(@PathVariable Long id, @PathVariable Long id2) {
        return sellerServices.getInfoSellerProductWithId(id, id2);

    }
    @DeleteMapping ("{id}/product/{id2}")
    public void deleteProductComment(@PathVariable Long id, @PathVariable Long id2,Long deleteComment,Principal principal) {
         sellerServices.deleteComment(id, id2,deleteComment,principal.getName());

    }
    @PostMapping("{id}/product/{id2}")
    public void addProductComment(@PathVariable Long id, @PathVariable Long id2,@RequestBody(required = true) CommentDTO commentDTO,Principal principal) {
         sellerServices.addComment(id, id2,commentDTO,principal.getName());

    }
    @PutMapping("{id}/product/{id2}")
    public void addProductLike(@PathVariable Long id, @PathVariable Long id2,@RequestParam(required = true) Boolean like,Principal principal) {
        sellerServices.addLike(id, id2,like, principal.getName());

    }



    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")
    @PutMapping("{id}/product/{id2}/update")
    public void updateSellerProductWithId(@PathVariable Long id, @PathVariable Long id2,
                                          @RequestParam(required = false) String description,
                                          @RequestParam(required = false) Integer quantity,
                                          @RequestParam(required = false) Double price,
                                          @RequestParam(required = false) List<String> size,
                                          @RequestParam(required = false) String type,
                                          @RequestParam(required = false) String brand,
                                          @RequestParam(required = false) String category
                                          ) {
        sellerServices.updateSellerProductWithId(id, id2, description, quantity,
                price,  size, type, brand, category);
    }
    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")
    @PostMapping("{id}/product/{id2}/delete-image")
    public void deleteSellerImageProductWithId(@PathVariable Long id,@PathVariable Long id2 ,@RequestBody ColorPropsDTO colorPropsDTO){
        sellerServices.deleteSellerImageProductWithId(id,id2,colorPropsDTO);
    }
    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")
    @PostMapping("{id}/product/{id2}/add-image")
    public void addSellerImageProductWithId(@PathVariable Long id,@PathVariable Long id2 ,@RequestBody ColorPropsDTO colorPropsDTO){
        sellerServices.addSellerImageProductWithId(id,id2,colorPropsDTO);
    }
    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")
    @PostMapping("{id}/update-image")
    public void updateSellerImage(@PathVariable Long id,@RequestBody ImageDto image){
        sellerServices.updateSellerImage(id,image);
    }

    @GetMapping("/")
    @JsonView(View.View1.class)
    public List<User> sellers() {

        return sellerServices.showSellers();
    }
    @DeleteMapping("/")
    public void deleteCommentFromSellerComments1(@RequestParam(required = true)Long sellerId,@RequestParam(required = true)Long deleteComment,Principal principal){
        sellerServices.deleteCommentFromSellerComments1(sellerId,deleteComment,principal.getName());
    }

    @PostMapping("/")
    public void addCommentToSeller1(@RequestParam(required = true)Long sellerId,@RequestBody(required = true)CommentDTO commentDTO,Principal principal){
        sellerServices.addCommentToSeller1(sellerId,commentDTO,principal.getName());
    }
    @PutMapping("/")
    public void addFollow1(@RequestParam(required = true)Long sellerId,@RequestParam(required = true) Boolean follow,Principal principal){
        sellerServices.addFollow1(sellerId,follow,principal.getName());
    }

   /* @GetMapping("/")
    public String sellers() {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user=userRepo.findUserByEmail("9@gmail.com");
        return authentication.getPrincipal().toString()+"9999"+user.get().getRoles().stream().findFirst().get().getName();
    }*/

    @PreAuthorize("hasAuthority('SELLER') and #id==authentication.principal.id")

    @DeleteMapping("{id}/product/{id2}/delete")
    public void deleteProduct(@PathVariable Long id, @PathVariable Long id2) {
        sellerServices.deleteProduct(id, id2);
    }

    @GetMapping("/{id}/seller-comments")

    public List<CommentFromDto> showSellerComments(@PathVariable Long id) {
        return sellerServices.showSellerComment(id);
    }
    @GetMapping("/{id}/follower")
    @JsonView(View.View1.class)
    public List showFollower(@PathVariable Long id){
        return sellerServices.showSellerFollower(id);
    }
}