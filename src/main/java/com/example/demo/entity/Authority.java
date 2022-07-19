package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Authority")
@Table(name="authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="authority_id",
            updatable = false
    )
    private Long AuthorityId;
    @Column(
            name="authority"
    )
    private String authority;
    @ManyToMany(

            mappedBy = "authorities"


    )
    @JsonIgnore
    private List<Role>roles=new ArrayList<>();

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public Long getAuthorityId() {
        return AuthorityId;
    }

    public void setAuthorityId(Long authorityId) {
        AuthorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "AuthorityId=" + AuthorityId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
