package com.tutorial.tunjiTech.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO{
    private static final List<Customer> customers;
    static{
        customers = new ArrayList<>();
        Customer cust1 = new Customer(1, "Oba", "oba@gmail.com", 14);
        Customer cust2 = new Customer(2, "Semi", "semi@yahoo.com", 11);
        customers.add(cust1);
        customers.add(cust2);
    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerByID(Integer customerId) {
       return customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst();

    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void deleteCustomerByID(Integer id) {
        customers.remove(customers.stream()
                        .filter(c -> c.getId()
                                .equals(id)).findFirst());
    }


    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer Id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(Id));
    }
}
