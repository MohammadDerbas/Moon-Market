package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity(name="ImgProfilePic")
@Table(name="img_profile_pic")
public class ImgProfilePic {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonView({View.View1.class, View.View3.class})

    @Column(name = "name")
    private String name;
    @JsonView({View.View1.class, View.View3.class})

    @Column(name = "type")
    private String type;
    //@JsonView({View.View2.class,View.View4.class})

    @Column(name = "image")
    private byte[] image;

    @JsonView({View.View1.class, View.View3.class})
    private String url;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public ImgProfilePic() {
    }

    public ImgProfilePic(String name, String type, byte[] image,String url) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.url=url;
    }

    public ImgProfilePic(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
