package com.example.demo.DTO;

import java.util.Objects;

public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String address;
    private final String phone;
    private final String postalCode;
    private final Boolean isCustomer;
    private final Boolean isSeller;


    public RegistrationRequest(String firstName, String lastName, String email, String password, String address, String phone, String postalCode, Boolean isCustomer, Boolean isSeller) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
        this.isCustomer = isCustomer;
        this.isSeller = isSeller;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Boolean getCustomer() {
        return isCustomer;
    }

    public Boolean getSeller() {
        return isSeller;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(postalCode, that.postalCode) && Objects.equals(isCustomer, that.isCustomer) && Objects.equals(isSeller, that.isSeller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, address, phone, postalCode, isCustomer, isSeller);
    }
}
