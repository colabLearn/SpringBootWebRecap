package com.tutorial.tunjiTech.customer;

import com.tutorial.tunjiTech.exception.DuplicateResourceException;
import com.tutorial.tunjiTech.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers(){
      return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id){
        return customerDAO.selectCustomerByID(id)
                .orElseThrow(
                () -> new IllegalArgumentException(
                        "Customer with id %s not found".formatted(id)));
    }

    public void deleteCustomerById(Integer id){
       if(!customerDAO.existsPersonWithId(id)){
           throw new ResourceNotFoundException(
             "Customer with id does not exist"
           );
       }
        customerDAO.deleteCustomerByID(id);
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest){

        //Check if email exist
        String email = customerRegistrationRequest.email();
        if(customerDAO.existsPersonWithEmail(email)){

            throw new DuplicateResourceException(
                    "email already taken"
            );

        }

        //Otherwise add the customer
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDAO.insertCustomer(customer);

    }



}
