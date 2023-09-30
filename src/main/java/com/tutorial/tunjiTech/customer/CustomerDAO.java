package com.tutorial.tunjiTech.customer;


import java.util.List;
import java.util.Optional;


public interface CustomerDAO {

    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerByID(Integer Id);
}
