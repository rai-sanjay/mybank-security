package com.mybank.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.mybank.model.Customer;
import com.mybank.repository.ICustomerRepository;

@RestController
@RequestMapping("/api/")
public class LoginController {
	
	@Autowired
	private ICustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try {
        	//Hash password before save
        	String hashedPwd = passwordEncoder.encode(customer.getPassword());
        	//set it to Customer object before saving 
        	customer.setPassword(hashedPwd);
        	customer.setCreatedDate(LocalDate.now());
        	savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are successfully registered");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + ex.getMessage());
        }
        return response;
    }
	 
	@GetMapping("user")
	    public Customer getUserDetailsAfterLogin(Authentication authentication) {
	        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
	        if (customers.size() > 0) {
	            return customers.get(0);
	        } else {
	            return null;
	        }

	    }
}
