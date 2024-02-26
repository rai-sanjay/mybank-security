package com.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybank.model.Customer;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
	
	List<Customer> findByEmail(String email);

}
