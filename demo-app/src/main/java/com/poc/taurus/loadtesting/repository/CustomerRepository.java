package com.poc.taurus.loadtesting.repository;

import com.poc.taurus.loadtesting.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
