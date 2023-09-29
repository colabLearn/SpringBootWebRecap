package com.tutorial.tunjiTech.customer;

import com.tutorial.tunjiTech.exception.ResourceNotFound;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
      return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id){
        return customerDAO.selectCustomerByID(id)
                .orElseThrow(
                () -> new ResourceNotFound(
                        "Customer with id %s not found".formatted(id)
                ));
    }
}
