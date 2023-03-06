package com.niit.bej.repository;

import com.niit.bej.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {
    Customer findCustomerByNameAndPassword(String name, String password);
}
