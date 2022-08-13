package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServices {

    private final ProductRepo productRepo;
    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> listProduct() {
        return productRepo.findAll();
    }


    public List<Product> filter(String categoury, String color, List<String> size, String type, String brand,  Integer s, Integer e) {
        List<Product>filterProduct1=new ArrayList<>();

        ArrayList<ArrayList<Product>>  arr=new ArrayList<>();

        ArrayList<Product>catPro=new ArrayList<>();

        filterProduct1=productRepo.findAll();
        arr.add((ArrayList<Product>) filterProduct1);

        if (categoury != null && categoury.length() > 0) {
            catPro= (ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getCategory().getCategory().equals(categoury)).collect(Collectors.toList());
            arr.add(catPro);


        }
        if (color!= null && color.length() > 0) {
            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream()
                    .filter(product -> product.getColorProps().stream().anyMatch(colorProps -> colorProps.getColor().equals(color))).collect(Collectors.toList());
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

            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getType().getType().equals(type)).collect(Collectors.toList());
            arr.add(catPro);


        }
        if (brand != null && brand.length() > 0) {

            catPro=(ArrayList<Product>) arr.get(arr.size()-1).stream().filter(product -> product.getBrand().getBrand().equals(brand)).collect(Collectors.toList());
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


    }

