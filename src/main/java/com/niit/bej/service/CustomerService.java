package com.niit.bej.service;

import com.niit.bej.domain.Customer;


public interface CustomerService {
    Customer login(String name, String password);
}
