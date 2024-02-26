package com.mybank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mybank.model.Customer;
import com.mybank.repository.ICustomerRepository;

@SpringBootApplication
public class MybankSecurityApplication implements CommandLineRunner{
	
	@Autowired
	ICustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(MybankSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Customer> customers = customerRepository.findByEmail("rai.sanjay2015@outlook.com");
		if(null == customers || customers.isEmpty()) {
			Customer customer = new Customer();
			customer.setEmail("rai.sanjay2015@outlook.com");
			customer.setPassword("12345");
			customer.setRole("admin");
			Customer insertedCustomer = customerRepository.save(customer);
			System.out.println(insertedCustomer.getId());
		}
		
	}

}
