package com.niit.bej.controller;

import com.niit.bej.domain.Customer;
import com.niit.bej.exception.CustomerAlreadyExistException;
import com.niit.bej.exception.CustomerNotFoundException;
import com.niit.bej.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer/add")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.register(customer);
            return new ResponseEntity<>(customer1.getName() + "added to DataBase", HttpStatus.CREATED);
        } catch (CustomerAlreadyExistException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Customer Already Exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        try {

            return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.FOUND);
        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Customer Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Customer customer) {
        try {
            Customer loggedCustomer = customerService.login(customer.getName(), customer.getPassword());
            return new ResponseEntity<>(loggedCustomer, HttpStatus.ACCEPTED);
        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("please check username and password", HttpStatus.NOT_FOUND);
        }

    }
}
