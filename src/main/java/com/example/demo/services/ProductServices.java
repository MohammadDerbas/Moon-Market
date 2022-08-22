package com.example.demo.services;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.StoreHouseDto;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import com.example.demo.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServices {

    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    private final LikeRepo likeRepo;
    private final UserRepo userRepo;
    private final ProductCommentRepo productCommentRepo;
    private final StoreHouseRepo storeHouseRepo;
    @Autowired
    public ProductServices(ProductRepo productRepo, CustomerRepo customerRepo, LikeRepo likeRepo, UserRepo userRepo, ProductCommentRepo productCommentRepo, StoreHouseRepo storeHouseRepo) {
        this.productRepo = productRepo;
        this.customerRepo = customerRepo;
        this.likeRepo = likeRepo;
        this.userRepo = userRepo;
        this.productCommentRepo = productCommentRepo;
        this.storeHouseRepo = storeHouseRepo;
    }
    public List<Product> listProduct(Long productId, Boolean like, CommentDTO commentDTO,Long deleteComment, String email) {
        if(productId!=null){
            boolean exist=productRepo.existsById(productId);
            if(!exist){
                throw new ApiRequestException("product with id"+productId+"does not exist");
            }
        }
        long id=userRepo.findUserByEmail(email).get().getId();
        Boolean exist=userRepo.existsById(id);
        if(!exist){
            throw new ApiRequestException("user with id"+id+"does not exist");

        }
        boolean exist2=customerRepo.existsById(id);
        if(!exist2){
            throw new ApiRequestException("He  is not a customer try with customer id ");

        }
        Product product=productRepo.findProductById(productId);
        Customer customer=customerRepo.findUserByID(id).get();
        if(like!=null) {
            if(commentDTO!=null){
                throw new ApiRequestException("you try to like and comment in the same time");
            }
            if(deleteComment!=null){
                throw new ApiRequestException("you try to like and delete comment in the same time");

            }
            if (like) {
                Long id3 = customerRepo.findUserByEmail(email).get().getId();
                Like like1 = new Like(new LikeId(id3, productId), customerRepo.findUserByID(id3).get(), productRepo.findProductById(productId));
                likeRepo.save(like1);
            } else {
                if(!likeRepo.isCustmoerLikeProduct(id,productId)){
                    throw new ApiRequestException("customer with id +"+id+" did not like product with id"+id);

                }
                likeRepo.deleteById(new LikeId(id, productId));
            }
        }
        else {
            if (commentDTO != null) {
                if (deleteComment != null) {
                    throw new ApiRequestException("you try to add comment and delete comment in the same time");
                }
                String productComments = commentDTO.getComment();
                ProductComment productComment = new ProductComment(productComments);
                productComment.setProduct(product);
                productComment.setCustomer(customer);
                productCommentRepo.save(productComment);
            } else {
                if (deleteComment != null) {
                    if (!productCommentRepo.existsById(deleteComment, email)) {
                        throw new ApiRequestException("this comment with id" + deleteComment + "may not exist " + "or you can't delete it from this customer");

                    }
                    productCommentRepo.deleteById(deleteComment);
                }
            }
        }
        return productRepo.findAll();
    }

    public List<Product>  filter(String categoury, String color, List<String> size, String type, String brand,  Integer s, Integer e) {
        List<Product>filterProduct1=new ArrayList<>();

        ArrayList<ArrayList<Product>>  arr=new ArrayList<>();

        ArrayList<Product>catPro=new ArrayList<>();

        filterProduct1=productRepo.findAll();
        arr.add((ArrayList<Product>) filterProduct1);

        if (categoury != null && categoury.length() > 0) {
            catPro= (ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getCategory().getCategory().equalsIgnoreCase(categoury)).collect(Collectors.toList());
            arr.add(catPro);


        }
        if (color!= null && color.length() > 0) {
            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream()
                    .filter(product -> product.getColorProps().stream().anyMatch(colorProps -> colorProps.getColor().equalsIgnoreCase(color))).collect(Collectors.toList());
            arr.add(catPro);

        }
        if (size != null ) {
            Integer c=0;
            ArrayList<Product>ss=new ArrayList<>();
                for (Product product : arr.get(arr.size() - 1)) {
                    for(String s1:size) {

                    for (Size size1 : product.getSizes()) {

                        if (size1.getSize().equals(s1)) {
                            c++;
                        }
                        if (c == (size.size())) {
                            ss.add(product);
                            break;
                        }
                    }
                }
                    c = 0;
                }


            arr.add(ss);



        }
        if (type != null && type.length() > 0) {

            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getType().getType().equalsIgnoreCase(type)).collect(Collectors.toList());
            arr.add(catPro);


        }
        if (brand != null && brand.length() > 0) {

            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getBrand().getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
            arr.add(catPro);


        }
        if (s != null  &&s>0) {

            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getPrice()>=s).collect(Collectors.toList());
            arr.add(catPro);




        }
        if (e != null&&e<1000000000) {

            catPro = (ArrayList<Product>) arr.get(arr.size() - 1).stream().filter(product -> product.getPrice() <= e).collect(Collectors.toList());
            arr.add(catPro);
        }
            return arr.get(arr.size()-1);

        }

    public Integer numberOfLikesOnProduct(Long productId) {
        boolean exist=productRepo.existsById(productId);
        if(!exist){
            throw new ApiRequestException("product with id "+productId+"does not exist");
        }
        return likeRepo.countLikeByProductId(productId);
    }

    public void addProductComment(Long productId, CommentDTO commentDTO, String name) {
        boolean exist1 = productRepo.existsById(productId);
        if (!exist1) {
            throw new ApiRequestException("product with id" + productId + "does not exist");
        }
        long id2 = userRepo.findUserByEmail(name).get().getId();

        boolean exist2 = customerRepo.existsById(id2);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");

        }
        Product product = productRepo.findProductById(productId);
        Customer customer = customerRepo.findUserByID(id2).get();
        String productComments = commentDTO.getComment();
        ProductComment productComment = new ProductComment(productComments);
        productComment.setProduct(product);
        productComment.setCustomer(customer);
        productCommentRepo.save(productComment);
    }

    public void addProductLike(Long productId, Boolean like, String name) {
        boolean exist1 = productRepo.existsById(productId);
        if (!exist1) {
            throw new ApiRequestException("product with id" + productId + "does not exist");
        }
        long id2 = userRepo.findUserByEmail(name).get().getId();

        boolean exist2 = customerRepo.existsById(id2);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");

        }
        Product product = productRepo.findProductById(productId);
        Customer customer = customerRepo.findUserByID(id2).get();
        Like like1 = new Like(new LikeId(id2, productId), customerRepo.findUserByID(id2).get(), productRepo.findProductById(productId));
        if (like) {
            likeRepo.save(like1);
        } else {
            if (!likeRepo.isCustmoerLikeProduct(id2, productId)) {
                throw new ApiRequestException("customer with id +" + id2 + " did not like product with id" + productId);

            }
            likeRepo.delete(like1);


        }
    }
    public StoreHouseDto gerProductAndSellerId(Long id){
        boolean exist=productRepo.existsById(id);
        if(!exist){
            throw new ApiRequestException("this product is not exist");
        }
        System.out.println(convertToDto(storeHouseRepo.findStoreHouseByProductId(id)));
       return convertToDto(storeHouseRepo.findStoreHouseByProductId(id));
    }
    public StoreHouseDto convertToDto(StoreHouse storeHouse){
        StoreHouseDto storeHouseDto=new StoreHouseDto();
        storeHouseDto.setProduct(storeHouse.getProduct());
        storeHouseDto.setSellerId(storeHouse.getSeller().getId());
        return storeHouseDto;
    }

    public void deleteProductComment(Long productId, Long deleteComment, String name) {
        boolean exist1 = productRepo.existsById(productId);
        if (!exist1) {
            throw new ApiRequestException("product with id" + productId + "does not exist");
        }
        long id2 = userRepo.findUserByEmail(name).get().getId();

        boolean exist2 = customerRepo.existsById(id2);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");

        }
        Product product = productRepo.findProductById(productId);
        Customer customer = customerRepo.findUserByID(id2).get();
        if (!productCommentRepo.existsById(deleteComment, name)) {
            throw new ApiRequestException("this comment with id" + deleteComment + "may not exist " + "or you can't delete it from this customer");

        }
        productCommentRepo.deleteById(deleteComment);
    }
}

