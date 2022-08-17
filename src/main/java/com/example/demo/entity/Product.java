package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Product")
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    @JsonView({View.View2.class,View.View4.class})
    private Long id;
    @Column(name = "description",nullable = false )
    @JsonView({View.View2.class,View.View4.class})

    private String description;
    @Column(name = "quantity",nullable = false )
    @JsonView({View.View2.class,View.View4.class})

    private Integer quantity;
    @Transient
    private Integer points;
    @Column(name = "price",nullable = false )
    @JsonView({View.View2.class,View.View4.class})

    private Double price;

    @Column(name = "num_Product_Purchaised",columnDefinition = "integer default 0",insertable = false)
    private Integer numberOfProductPurchaised;
    @JsonView({View.View2.class,View.View4.class})
    LocalDate localDate=LocalDate.now();
    @Column(name = "date",insertable = false,columnDefinition = "Date DEFAULT CURRENT_DATE")
    private Date date;
    @OneToMany(
            mappedBy = "product"
    )
    private List <Like> likes=new ArrayList<>();
    public Product() {
    }

    public Product(String description, Integer quantity, Double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.numberOfProductPurchaised=0;
    }

    public Product(String description, Integer quantity, Double price, List<Size> sizes,List<ColorProps> colorProps, Type type, Brand brand, Category category) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.sizes = sizes;
        this.colorProps = colorProps;
        this.type = type;
        this.brand = brand;
        this.category = category;
        this.numberOfProductPurchaised=0;

    }

    public Product(String description, Integer quantity, Double price, List<Size> sizes, Type type, Brand brand, Category category) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.sizes = sizes;
        this.type = type;
        this.brand = brand;
        this.category = category;
        this.numberOfProductPurchaised=0;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }
    @ManyToMany(
    )
    @JoinTable(
            name = "product_size_id",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    foreignKey = @ForeignKey(name="product_size_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name="size_id",
                    foreignKey = @ForeignKey(name = "size_product_id")

            )


    )
    @JsonView({View.View2.class,View.View4.class})
    private List<Size> sizes=new ArrayList<>();
 @OneToMany(
         mappedBy = "product"
 )
    @JsonView({View.View2.class,View.View4.class})
    private List<ColorProps> colorProps;
    @ManyToOne
    @JoinColumn(
            name = "product_type_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "product_type_id")
    )
    @JsonView({View.View2.class,View.View4.class})

    private Type type;


    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(
            mappedBy = "product"
    )
    private List <ProductComment> comments=new ArrayList<>();

    @ManyToOne
    @JoinColumn(
            name = "product_brand_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "product_brand_id")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonView({View.View2.class,View.View4.class})

    private Brand brand;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.REMOVE
    )
    private List <Order> carts=new ArrayList<>();
    @ManyToOne
    @JoinColumn(
            name="product_category_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name ="product_category_id_fk" )

    )
    @JsonView({View.View2.class,View.View4.class})
    private Category category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE)
    private List <StoreHouse> storeHouses = new ArrayList<>();

    public List<StoreHouse> getStoreHouses() {
        return storeHouses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfProductPurchaised() {
        return numberOfProductPurchaised;
    }

    public void setNumberOfProductPurchaised(Integer numberOfProductPurchaised) {
        this.numberOfProductPurchaised = numberOfProductPurchaised;
    }



    public List<ProductComment> getComments() {
        return comments;
    }

    public void setComments(List<ProductComment> comments) {
        this.comments = comments;
    }



    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    public void addSize(Size size) {

            sizes.add(size);
            System.out.println(sizes + "2222222222222222222222222");
            // size.addProduct(this);

    }
    public void addSizeB(Size size) {

        //sizes.add(size);
        System.out.println(sizes + "5555555555555555555");
        // size.addProduct(this);

    }

    public void removeSize(Size size){
        if(sizes.contains(size)){
            sizes.remove(size);
            size.setProducts(null);
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getCarts() {
        return carts;
    }

    public void setCarts(List<Order> carts) {
        this.carts = carts;
    }

    public List<ColorProps> getColorProps() {
        return colorProps;
    }

    public void setColorProps(List<ColorProps> colorProps) {
        this.colorProps = colorProps;
    }

    public void buy(){

        this.setQuantity(this.getQuantity()-1);
        this.numberOfProductPurchaised+=1;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", points=" + points +
                ", price=" + price +
                '}';
    }
}
