package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Role")
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name="role_id",
            updatable = false
    )
    private Long roleId;
    @Column(
            name="role",
            nullable = false
    )
    private String role;
    @ManyToMany(
            cascade ={ CascadeType.PERSIST,CascadeType.REMOVE}
    )
    @JoinTable (
            name="role_authority",
            joinColumns= @JoinColumn(
                    name="role_id",
                    foreignKey = @ForeignKey(name = "roleauthority_role_id_fk")

    ),
            inverseJoinColumns = @JoinColumn(
                    name="authority_id",
                    foreignKey = @ForeignKey(name = "roleauthority_authority_id_fk")
    )
    )
    private List <Authority>authorities=new ArrayList<>();

    @OneToMany(
            mappedBy = "role",
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}

        )
    @JsonIgnore
    private List <User> users=new ArrayList<>();
    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addAuthority(Authority authority) {
        if (!authorities.contains(authority)) {
            authorities.add(authority);
            authority.getRoles().add(this);
        }
    }
        public void removeAuthority(Authority authority){
            if(authorities.contains(authority)){
                authorities.remove(authority);
                authority.getRoles().remove(this);
            }

    }
    public void addUser(User user){
        if(!users.contains(user)){
            users.add(user);
            user.setRole(this);
        }
    }
    public void removeUser(User user){
        if(users.contains(user)){
            users.remove(user);
            user.setRole(null);
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }
}
