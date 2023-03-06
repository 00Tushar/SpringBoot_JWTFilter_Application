package com.niit.bej.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Document
public class Customer {
    @MongoId
    private long id;
    private String name;
    private String password;
    private String phoneNo;

    public Customer(long id, String name, String password, String phoneNo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(password, customer.password) && Objects.equals(phoneNo, customer.phoneNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, phoneNo);
    }
}
