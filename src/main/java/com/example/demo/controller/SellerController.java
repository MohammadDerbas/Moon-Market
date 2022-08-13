package com.example.demo.controller;

import com.example.demo.DTO.ColorPropsDTO;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.CommentFromDto;
import com.example.demo.DTO.ImageDto;
import com.example.demo.entity.*;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.SellerServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("{id}/product/add")
    public void addNewProduct(@PathVariable Long id, @RequestBody Product product) {
        sellerServices.addNewProduct(id, product);
    }

    @GetMapping("{id}/product")
    @JsonView(View.View2.class)
    public List<Product> showProduct(@PathVariable Long id, @RequestParam (required = false)Long productId,@RequestParam(required = false) Boolean like,Principal principal) {
        return sellerServices.showProduct(id,productId,like,principal.getName());
    }

    @GetMapping("{id}/product/{id2}")
    @JsonView(View.View2.class)
    public Product InfoSellerProductWithId(@PathVariable Long id, @PathVariable Long id2,@RequestParam(required = false) Boolean like,Principal principal) {
        return sellerServices.getInfoSellerProductWithId(id, id2,like,principal.getName());

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