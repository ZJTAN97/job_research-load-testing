package com.poc.taurus.loadtesting.service;


import com.poc.taurus.loadtesting.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustomers();

    Customer createCustomer(Customer customer);
}
