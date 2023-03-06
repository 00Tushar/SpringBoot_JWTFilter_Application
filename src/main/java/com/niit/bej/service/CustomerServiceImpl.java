package com.niit.bej.service;

import com.niit.bej.domain.Customer;
import com.niit.bej.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer login(String name, String password) {
        Customer customer = customerRepository.findCustomerByNameAndPassword(name, password);
        if (customer != null) {
            return customer;
        }
        return null;
    }
}
