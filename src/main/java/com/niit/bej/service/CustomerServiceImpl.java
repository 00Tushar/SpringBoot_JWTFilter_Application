package com.niit.bej.service;

import com.niit.bej.domain.Customer;
import com.niit.bej.exception.CustomerAlreadyExistException;
import com.niit.bej.exception.CustomerNotFoundException;
import com.niit.bej.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer login(String name, String password) throws CustomerNotFoundException {
        Customer customer = customerRepository.findCustomerByNameAndPassword(name, password);
        if (customer != null) {
            return customer;
        }
            throw new CustomerNotFoundException("please check name and password");

    }

    @Override
    public Customer register(Customer customer) throws CustomerAlreadyExistException {
        Customer customer1 = customerRepository.findCustomerByNameAndPassword(customer.getName(), customer.getPassword());
        if (customer1 != null) {
            throw new CustomerAlreadyExistException("Already Exist");
        } else
            return customerRepository.save(customer);

    }

    @Override
    public List<Customer> getAllCustomer() throws CustomerNotFoundException {
        List<Customer> fetchCustomer = customerRepository.findAll();
        if (fetchCustomer.isEmpty()) {
            throw new CustomerNotFoundException("No Customer found");
        }
        return fetchCustomer;
    }

    @Override
    public boolean deleteCustomer(Long id) throws CustomerNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        } else {
            throw new CustomerNotFoundException("Customer not found with that id");
        }
    }
}
