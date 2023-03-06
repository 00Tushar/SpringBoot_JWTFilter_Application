package com.niit.bej.service;

import com.niit.bej.domain.Customer;
import com.niit.bej.exception.CustomerNotFoundException;


public interface CustomerService {
    Customer login(String name, String password) throws CustomerNotFoundException;
}
