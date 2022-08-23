package com.example.demo.services;

import com.example.demo.DTO.*;
import com.example.demo.Util.ImageUtility;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class SellerServices {
    private final SellerRepo sellerRepo;
    private final UserRepo userRepo;
    private final StoreHouseRepo storeHouseRepo;
    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final TypeRepo typeRepo;
    private final CategoryRepo categoryRepo;
    private final SizeRepo sizeRepo;
    private final SellerCommentRepo sellerCommentRepo;
    private final FollowRepo followRepo;
    private final ColorPropsRepo colorPropsRepo;
    private final CustomerRepo customerRepo;
    private final LikeRepo likeRepo;
    private final ProductCommentRepo productCommentRepo;
    private final ImgRepo imgRepo;
    private final OrderRepo orderRepo;

    @Autowired
    public SellerServices(SellerRepo sellerRepo, @Qualifier("sellerRepo") UserRepo userRepo, StoreHouseRepo storeHouseRepo, ProductRepo productRepo, BrandRepo brandRepo, TypeRepo typeRepo, CategoryRepo categoryRepo, SizeRepo sizeRepo, SellerCommentRepo sellerCommentRepo, FollowRepo followRepo, ColorPropsRepo colorPropsRepo, CustomerRepo customerRepo, LikeRepo likeRepo, ProductCommentRepo productCommentRepo, ImgRepo imgRepo, OrderRepo orderRepo) {
        this.sellerRepo = sellerRepo;
        this.userRepo = userRepo;
        this.storeHouseRepo = storeHouseRepo;
        this.productRepo = productRepo;
        this.brandRepo = brandRepo;
        this.typeRepo = typeRepo;
        this.categoryRepo = categoryRepo;
        this.sizeRepo = sizeRepo;
        this.sellerCommentRepo = sellerCommentRepo;
        this.followRepo = followRepo;
        this.colorPropsRepo = colorPropsRepo;
        this.customerRepo = customerRepo;
        this.likeRepo = likeRepo;
        this.productCommentRepo = productCommentRepo;
        this.imgRepo = imgRepo;
        this.orderRepo = orderRepo;
    }

    public Product getInfoSellerProductWithId(Long id, Long id2) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        boolean productExists = productRepo.existsById(id2);
        if (!productExists) {
            throw new ApiRequestException("Product with id " + id2 + "does not exist");
        }


        return productRepo.getReferenceById(id2);
    }

    public Optional<Seller> getSeller(Long id) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }

        Optional<Seller> seller = sellerRepo.findUserById(id);
        return seller;

    }
    public Optional<Seller> getSellerByPrincipal(String name) {
        boolean exists = sellerRepo.existsByEmail(name);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + name + "does not exist");
        }
        Long sellerId = sellerRepo.findIdByEmail(name);
        Optional<Seller> seller = sellerRepo.findUserById(sellerId);
        return seller;

    }

    public void updateSellerInfo(String name, String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {

       Long id = sellerRepo.findUserByEmail(name).get().getId();
        boolean exists = userRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        User seller = userRepo.getReferenceById(id);

        if (email != null && email.length() > 0 && !Objects.equals(seller.getEmail(), email)) {
            Optional<User> studentOptional = userRepo.findUserByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ApiRequestException("email taken");
            }
            seller.setEmail(email);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(seller.getFirstName(), firstName)) {

            seller.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(seller.getLastName(), lastName)) {

            seller.setLastName(lastName);
        }
        if (address != null && address.length() > 0 && !Objects.equals(seller.getAddress(), address)) {

            seller.setAddress(address);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(seller.getPhone(), phone)) {

            seller.setPhone(phone);
        }
        if (postalCode != null && postalCode.length() > 0 && !Objects.equals(seller.getPostalCode(), postalCode)) {

            seller.setPostalCode(postalCode);
        }
        if (password != null && password.length() > 0 && !Objects.equals(seller.getPassword(), password)) {

            seller.setPassword(password);
        }

        userRepo.save(seller);
    }

    public void addNewProduct(Long id,String description,Integer quantity,Double price,String type,String brand,String category,List<String>sizes,List<String>color,List<MultipartFile>file1) throws IOException {
        System.out.println(description+quantity+price+type+brand+category);
        sizes.stream().forEach(s -> System.out.println(s));
        file1.stream().forEach(multipartFile -> System.out.println(multipartFile.getOriginalFilename()));


        Product product=new Product();
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setType(new Type(type));
        product.setBrand(new Brand(brand));
        product.setCategory(new Category(category));
        List<Size>sizesList=new ArrayList<>();
        sizes.stream().forEach(s -> sizesList.add(new Size(s)));
        product.setSizes(sizesList);
        product.setCategory(new Category(category));

        System.out.println(product);
        System.out.println(product.getBrand().getBrand());
        System.out.println(product.getCategory().getCategory());
        System.out.println(product.getType().getType());
        product.getSizes().stream().forEach(size -> System.out.println(size));
       // product.getColorProps().forEach(colorProps2 -> {System.out.println(colorProps2.getColor());
          //  colorProps2.getImages().stream().forEach(img -> System.out.println(img.getName()));});
        //Product product=new Product();
        boolean exists = brandRepo.existsByBrand(product.getBrand().getBrand());
        brandRepo.findByBrand(product.getBrand().getBrand()).addProduct(product);
        if (!exists)
            throw new ApiRequestException("this brand does not exist");
        boolean exists1 = typeRepo.existsByType(product.getType().getType());
        typeRepo.findByType(product.getType().getType()).addProduct(product);
        if (!exists1)
            throw new ApiRequestException("this type does not exist");












        List<String> s = new ArrayList<>();

        product.getSizes().stream().forEach(size -> s.add(size.getSize()));
        boolean exists2 = sizeRepo.existsBySizeIn(s);

        List<Size> sizes2 = sizeRepo.findBySize(s);
        System.out.println(sizes2 + "66666666666666666666");
        ArrayList<Size> ss = new ArrayList<>();
        product.setSizes(ss);

        sizeRepo.findBySize(s).forEach(size -> size.addProduct(product));//add product
        System.out.println(sizeRepo.findBySize(s) + "888888888888");

        if (!exists2)
            throw new ApiRequestException("this size does not exist");
        boolean exists3 = categoryRepo.existsByCategory(product.getCategory().getCategory());
        categoryRepo.findByCategory(product.getCategory().getCategory()).addProduct(product);
        if (!exists3)
            throw new ApiRequestException("this category does not exist");

        productRepo.save(product);
        color.stream().forEach(s1 -> {
            ColorProps colorProps = new ColorProps(s1);
            colorProps.setProduct(product);
            colorPropsRepo.save(colorProps);
            for (MultipartFile file:file1) {
                String [] split=file.getOriginalFilename().split("-");
                System.out.println(split[0]);
                if(split[0].equals(colorProps.getColor())) {
                    Img img = null;
                    try {
                        img = new Img(file.getOriginalFilename(), file.getContentType(), ImageUtility.compressImage(file.getBytes()),"http://localhost:8080/img/get/image/"+file.getOriginalFilename());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img.setColorProps(colorProps);
                    imgRepo.save(img);
                }
            }
            } );










        //User seller=  sellerRepo.getReferenceById(id);
        Optional<Seller> seller = sellerRepo.findUserById(id);
        StoreHouse op = new StoreHouse(new StoreHouseId(id, product.getId()), seller.get(), product);
        storeHouseRepo.save(op);
    }

    public List<Product> showProduct(Long id) {

        return productRepo.showProductWithSpecificSeller(id);

    }

    public List<User> showSellers() {

        List<User> sellers = sellerRepo.findAll();
        return sellers;
    }

    public void updateSellerProductWithId(Long id, Long id2, String description, Integer quantity, Double price, List<String> size, String type, String brand, String category) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        boolean productExists = productRepo.existsById(id2);
        if (!productExists) {
            throw new ApiRequestException("Product with id " + id2 + "does not exist");
        }
        Product product = productRepo.getReferenceById(id2);

        if (description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {

            product.setDescription(description);
        }
        if (price != null) {
            if (price < 0) {
                throw new ApiRequestException("price can't be negative number");
            } else if (price == 0) {
                throw new ApiRequestException("price can't be zero");
            } else {
                product.setPrice(price);
            }

        }
        if (quantity != null) {
            if (quantity < 0) {
                throw new ApiRequestException("quantity can not be negative number");
            } else {
                product.setQuantity(quantity);
            }

        }
        if (size != null) {


            boolean exists2 = sizeRepo.existsBySizeIn(size);
            if (!exists2) {
                throw new ApiRequestException("one of the sizes" + size + "does not exist");

            }

            ArrayList<Size> ss = new ArrayList<>();
            product.setSizes(ss);

            sizeRepo.findBySize(size).forEach(size3 -> size3.addProduct(product));


        }
        if (type != null && type.length() > 0) {
            boolean exist = typeRepo.existsByType(type);
            if (!exist) {
                throw new ApiRequestException("type" + type + "does not exist");

            }

            product.setType(typeRepo.findByType(type));
        }
        if (brand != null && brand.length() > 0) {
            boolean exist = brandRepo.existsByBrand(brand);
            if (!exist) {
                throw new ApiRequestException("brand" + brand + "does not exist");

            }

            product.setBrand(brandRepo.findByBrand(brand));
        }
        if (category != null && category.length() > 0) {
            boolean exist = categoryRepo.existsByCategory(category);
            if (!exist) {
                throw new ApiRequestException("category" + category + "does not exist");

            }

            product.setCategory(categoryRepo.findByCategory(category));
        }
        productRepo.save(product);

    }

    public void deleteProduct(Long id, Long id2) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id + "does not exist");
        }
        productRepo.deleteById(id2);
    }

    public Long findIdByEmail(Object principal) {
        return sellerRepo.findIdByEmail(principal.toString());
    }

    public List<CommentFromDto> showSellerComment(Long id) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }

        return sellerCommentRepo.findAll(id).stream().map(this::convertToDTO).collect(Collectors.toList());


    }


    private CommentFromDto convertToDTO(SellerComment sellerComment) {

        CommentFromDto dto = new CommentFromDto();
        dto.setComment(sellerComment.getComment());
dto.setRating(sellerComment.getRating());
        dto.setFrom(sellerComment.getCustomer().getFirstName() + " " + sellerComment.getCustomer().getLastName());
        dto.setId(sellerComment.getId());
        dto.setTime(sellerComment.getTime());
        dto.setImg(sellerComment.getCustomer().getImgProfilePic().getUrl());

        return dto;
    }


    public List showSellerFollower(Long id) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        return followRepo.findCustomerBySellerId(id);
    }

    public void deleteSellerImageProductWithId(Long id, Long id2, ColorPropsDTO colorPropsDTO) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id + "does not exist");
        }
        boolean exist2 = colorPropsRepo.existsByColor(colorPropsDTO.getColor(), id2);
        if (!exist2) {
            throw new ApiRequestException("color " + colorPropsDTO.getColor() + "does not exist for this product");
        }
        boolean exist3 = imgRepo.existsByImages(colorPropsDTO.getImages());
        if (!exist3) {
            throw new ApiRequestException("image does not exist for this product");
        }
        ColorProps colorProps = colorPropsRepo.findByColor(colorPropsDTO.getColor(), id2);
        System.out.println(colorProps + "000000000000000000002");
        System.out.println(colorProps.getImages().size() + "000000000000000000000");
        System.out.println(colorProps.getImages());
        if (colorProps.getImages().size() == 1) {
            colorPropsRepo.deleteByColor(colorPropsDTO.getColor());
        }
        else {

            imgRepo.deleteByImages(imgRepo.findByName(colorPropsDTO.getImages()).get());
            System.out.println(colorProps.getImages().size() + "000000000000000000001");
            System.out.println(colorProps.getImages());
        }
    }



    public void updateSellerImage(Long id, MultipartFile multipartFile) throws IOException {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        Optional<Seller> user = sellerRepo.findUserById(id);
        userRepo.updateUserImage(new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/profile_pic/image/"+multipartFile.getOriginalFilename())
        );

    }


    public void deleteComment(Long id, Long id2, Long deleteComment, String name) {
        if (deleteComment != null) {
            long id3 = userRepo.findUserByEmail(name).get().getId();
            boolean exist2 = customerRepo.existsById(id3);
            if (!exist2) {
                throw new ApiRequestException("He  is not a customer try with customer id ");
            }
            if (!exist2) {
                throw new ApiRequestException("He  is not a customer try with customer id ");
            }
            if (!productCommentRepo.existsById(deleteComment, name)) {
                throw new ApiRequestException("this comment with id" + deleteComment + "may not exist " + "or you can't delete it from this customer");

            }
            productCommentRepo.deleteById(deleteComment);
        }
    }

    public void addComment(Long id, Long id2, CommentDTO commentDTO, String name) {
        long id3 = userRepo.findUserByEmail(name).get().getId();
        boolean exist2 = customerRepo.existsById(id3);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");
        }
        Product product = productRepo.findProductById(id2);
        Customer customer = customerRepo.findUserByID(id3).get();


        String productComments = commentDTO.getComment();
        ProductComment productComment = new ProductComment(productComments);
        productComment.setProduct(product);
        productComment.setCustomer(customer);
        productCommentRepo.save(productComment);


    }

    public void addLike(Long id, Long id2, Boolean like, String name) {
        long id3 = userRepo.findUserByEmail(name).get().getId();
        boolean exist2 = customerRepo.existsById(id3);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");
        }
        Product product = productRepo.findProductById(id2);
        Customer customer = customerRepo.findUserByID(id3).get();


        Like like1 = new Like(new LikeId(id3, id2), customerRepo.findUserByID(id3).get(), productRepo.findProductById(id2));
        if (like) {
            likeRepo.save(like1);
        } else {
            likeRepo.delete(like1);
        }
    }

    public void addComment1(Long id, Long productId, CommentDTO commentDTO, String name) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
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

    public void deleteComment1(Long id, Long productId, Long deleteComment, String name) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
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

    public void addLike1(Long id, Long productId, Boolean like, String name) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
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
                throw new ApiRequestException("customer with id +" + id2 + " did not like product with id" + id);

            }
            likeRepo.delete(like1);


        }
    }

    public void deleteCommentFromSellerComments(Long id, Long deleteComment, String name) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(id);
        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        if (!sellerCommentRepo.existsById(deleteComment, name)) {
            throw new ApiRequestException("this comment with id" + deleteComment + "may not exist " + "or you can't delete it from this customer");

        }
        sellerCommentRepo.deleteById(deleteComment);
    }

    public void addCommentToSeller(Long id, CommentDTO commentDTO, String name) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(id);
        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        String sellerComments = commentDTO.getComment();
        Double sellerRatings=commentDTO.getRating();
        SellerComment sellerComment = new SellerComment(sellerComments,sellerRatings);
        sellerComment.setSeller(seller.get());
        sellerComment.setCustomer(customer);
        sellerCommentRepo.save(sellerComment);
    }

    public void addFollow(Long id, Boolean follow, String name) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(id);
        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        Followers followers = new Followers(new FollowersId(customer.getId(), id), customer, seller.get());
        if (follow) {
            followRepo.save(followers);
        } else {
            if (!followRepo.isCustmoerFollowSeller(customer.getId(), id)) {
                throw new ApiRequestException("customer with id " + customer.getId() + "did not follow seller with id" + id);

            } else {
                followRepo.delete(followers);
            }
        }
    }

    public void deleteCommentFromSellerComments1(Long sellerId, Long deleteComment, String name) {
        boolean exists = sellerRepo.existsById(sellerId);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + sellerId + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(sellerId);

        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        if (!sellerCommentRepo.existsById(deleteComment, name)) {

            throw new ApiRequestException("this comment with id" + deleteComment + "may not exist " + "or you can't delete it from this customer");
        }
        sellerCommentRepo.deleteById(deleteComment);
    }


//    This for adding review to seller
    public List<CommentFromDto> addCommentToSeller1(Long sellerId, CommentDTO commentDTO, String name) {
        boolean exists = sellerRepo.existsById(sellerId);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + sellerId + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(sellerId);

        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        String sellerComments = commentDTO.getComment();
        Double sellerRating= commentDTO.getRating();
        SellerComment sellerComment = new SellerComment(sellerComments,sellerRating);
        sellerComment.setSeller(seller.get());
        sellerComment.setCustomer(customer);
        sellerCommentRepo.save(sellerComment);
updateSellerRating(sellerId);
        return showSellerComment(sellerId);

    }


    public void updateSellerRating (Long sellerId){
        Seller seller = (Seller) sellerRepo.findUserById(sellerId).get();
        Integer ratingAvg= sellerCommentRepo.getRatingAvg(sellerId);


        seller.setStar(ratingAvg);
        sellerRepo.save(seller);







    }

    /* it ends here */


    public List<Product> getSellerProducts(String name){
        boolean exist1 = sellerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Seller seller = (Seller) sellerRepo.findUserByEmail(name).get();
        return productRepo.showProductWithSpecificSeller(seller.getId());



    }

    public void addFollow1(Long sellerId, Boolean follow, String name) {
        boolean exists = sellerRepo.existsById(sellerId);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + sellerId + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(sellerId);

        boolean exist1 = customerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        Followers followers = new Followers(new FollowersId(customer.getId(), sellerId), customer, seller.get());
        if (follow) {
            followRepo.save(followers);
        } else {
            if (!followRepo.isCustmoerFollowSeller(customer.getId(), sellerId)) {
                throw new ApiRequestException("customer with id " + customer.getId() + "did not follow seller with id" + sellerId);

            } else {
                followRepo.delete(followers);
            }
        }
    }

    public List<CommentFromDto> showComments(String name){


        boolean exist1 = sellerRepo.existsByEmail(name);
        if (!exist1) {
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Seller seller = (Seller) sellerRepo.findUserByEmail(name).get();
        return showSellerComment(seller.getId());

    }

    public List<CustomerProductDTO> getSellerOrders(String name) {
        Seller seller = (Seller) sellerRepo.findUserByEmail(name).get();

        List<Long> ids = productRepo.showProductIdsWithSpecificSeller(seller.getId());

        return orderRepo.getSellerOrdersByProductIds(ids).stream().map(this::convertEntityToDto).collect(Collectors.toList());


    }

    private CustomerProductDTO convertEntityToDto(Order order){
        AtomicReference<String> imgurl= new AtomicReference<>("https://static.zara.net/photos///2022/I/0/1/p/2569/263/400/2/w/385/2569263400_6_1_1.jpg?ts=1660213342477");
        order.getProduct().getColorProps().forEach(colorProps -> {
            if(colorProps.getColor()==order.getColor()){
                imgurl.set(colorProps.getImages().get(0).getUrl());
            }
        });
        CustomerProductDTO customerProductDTO=new CustomerProductDTO();
        customerProductDTO.setReference(order.getReference());
        customerProductDTO.setImgUrl(String.valueOf(imgurl));
        customerProductDTO.setUserId(order.getCustomer().getId());
        customerProductDTO.setFirstName(order.getCustomer().getFirstName());
        customerProductDTO.setLastName(order.getCustomer().getLastName());
        customerProductDTO.setAddress(order.getCustomer().getAddress());
        customerProductDTO.setPostalCode(order.getCustomer().getPostalCode());
        customerProductDTO.setPhone(order.getCustomer().getPhone());
        customerProductDTO.setDescription(order.getProduct().getDescription());
        customerProductDTO.setBrand(order.getProduct().getBrand());
        customerProductDTO.setDate((order.getDate()));
        customerProductDTO.setSize(order.getSize());
        customerProductDTO.setColor(order.getColor());
        customerProductDTO.setStatus(order.getStatus());
        customerProductDTO.setProductId(order.getProduct().getId());
        customerProductDTO.setPrice(order.getPrice());
        customerProductDTO.setQuantityOrder(orderRepo.quantity(order.getId()));
        return customerProductDTO;
    }

    public Integer getPendingOrders(String name) {
        Seller seller = (Seller) sellerRepo.findUserByEmail(name).get();

        List<Long> ids = productRepo.showProductIdsWithSpecificSeller(seller.getId());
        return orderRepo.getPendingOrdersCount(ids);





    }

    public void updateOrderStatus(String status, UUID reference) {

        Order order=orderRepo.getOrderByReference(reference);
        order.setStatus(status);
        order.setUpdatedAt(LocalDate.now());
        orderRepo.save(order);


    }
}

