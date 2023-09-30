package com.tutorial.tunjiTech.customer;


import java.util.List;
import java.util.Optional;


public interface CustomerDAO {

    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerByID(Integer Id);

    void insertCustomer(Customer customer);

    void deleteCustomerByID(Integer Id);

    boolean existsPersonWithEmail(String email);

    boolean existsPersonWithId(Integer Id);
    void updateCustomer(Customer update);
}
