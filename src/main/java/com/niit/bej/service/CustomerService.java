package com.niit.bej.service;

import com.niit.bej.domain.Customer;
import com.niit.bej.exception.CustomerAlreadyExistException;
import com.niit.bej.exception.CustomerNotFoundException;

import java.util.List;


public interface CustomerService {
    Customer login(String name, String password) throws CustomerNotFoundException;

    Customer register(Customer customer) throws CustomerAlreadyExistException;

    List<Customer> getAllCustomer() throws CustomerNotFoundException;

    boolean deleteCustomer(Long id) throws CustomerNotFoundException;
}
