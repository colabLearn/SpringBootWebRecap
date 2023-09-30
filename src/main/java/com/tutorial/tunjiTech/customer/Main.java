package com.tutorial.tunjiTech.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {


		SpringApplication.run(Main.class, args);
	}
	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){

		return args -> {
			Customer cust1 = new Customer( "Oba", "oba@gmail.com", 14);
			Customer cust2 = new Customer("Semi", "semi@yahoo.com", 11);

			List<Customer> customers = List.of(cust1,cust2);
			customerRepository.saveAll(customers);
		};
	}

}
