package com.tutorial.tunjiTech.customer;

import com.tutorial.tunjiTech.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}