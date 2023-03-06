package com.niit.bej.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
}
