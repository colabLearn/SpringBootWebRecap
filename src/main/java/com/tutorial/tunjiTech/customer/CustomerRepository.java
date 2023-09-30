package com.tutorial.tunjiTech.customer;

import com.tutorial.tunjiTech.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    boolean existsCustomerByEmail(String email);

}
