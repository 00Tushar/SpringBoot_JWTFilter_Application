package com.niit.bej.service;

import com.niit.bej.domain.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer login(String name, String password);
}
