package com.tutorial.tunjiTech.customer;

import com.tutorial.tunjiTech.exception.DuplicateResourceException;
import com.tutorial.tunjiTech.exception.RequestValidationException;
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


    public void updateCustomer(Integer customerId,
                               CustomerUpdateRequest updateRequest) {
        Customer customer = getCustomer(customerId);

        boolean changes = false;

        if(updateRequest.name() !=null
                && !updateRequest.name().equals(customer.getName()))
        {
            customer.setName(updateRequest.name());
            changes=true;
        }
        if(updateRequest.email() !=null
                && !updateRequest.email().equals(customer.getEmail()))
        {
            if(customerDAO.existsPersonWithEmail(updateRequest.email())){
                throw new DuplicateResourceException(
                  "email already taken"
                );
            }
            customer.setEmail(updateRequest.email());
            changes=true;
        }
        if(updateRequest.age() !=null
                && !updateRequest.age().equals(customer.getAge()))
        {
            customer.setAge(updateRequest.age());
            changes=true;
        }
        if(!changes){
           throw new RequestValidationException("no data changes found");
        }

        customerDAO.updateCustomer(customer);
    }
}
