package com.niit.bej.controller;

import com.niit.bej.domain.Customer;
import com.niit.bej.exception.CustomerAlreadyExistException;
import com.niit.bej.exception.CustomerNotFoundException;
import com.niit.bej.security.SecurityTokenGenerator;
import com.niit.bej.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public CustomerController(CustomerService customerService, SecurityTokenGenerator securityTokenGenerator) {
        this.customerService = customerService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("api/v1/customers/add")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        try {
            Customer customer1 = customerService.register(customer);
            return new ResponseEntity<>(customer1.getName() + " added to DataBase", HttpStatus.CREATED);
        } catch (CustomerAlreadyExistException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Customer Already Exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("api/v1/customers")
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
            if (loggedCustomer != null && loggedCustomer.getId() == customer.getId()) {
                Map<String, String> tokenMap = securityTokenGenerator.generateToken(customer);
                return new ResponseEntity<>(tokenMap, HttpStatus.ACCEPTED);
            } else
                throw new CustomerNotFoundException("please check username and password!");

        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("please check username and password!!!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("api/v1/customers/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.ACCEPTED);
        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Customer Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
